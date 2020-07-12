import os
from collections import Counter
from math import log2
import pickle


class ReviewClassifier:
    def __init__(self, pos_folder, neg_folder, pos_test_folder, neg_test_folder, save_path, refresh=False):
        self.pos_folder = pos_folder
        self.neg_folder = neg_folder
        self.pos_test_folder = pos_test_folder
        self.neg_test_folder = neg_test_folder

        if not refresh and (os.path.exists(f'{save_path}/pos') and os.path.exists(f'{save_path}/neg')):
            print('loading from pickle')
            with open(f'{save_path}/pos', 'rb') as file:
                self.occurrences_pos = pickle.load(file)
            with open(f'{save_path}/neg', 'rb') as file:
                self.occurrences_neg = pickle.load(file)
            return

        if not os.path.exists(save_path):
            os.mkdir(save_path)

        print('computing from 0')
        self.occurrences_pos = self.count_words_in_reviews(pos_folder, -1)
        self.occurrences_neg = self.count_words_in_reviews(neg_folder, -1)
        with open(f'{save_path}/pos', 'wb') as file:
            pickle.dump(self.occurrences_pos, file)

        with open(f'{save_path}/neg', 'wb') as file:
            pickle.dump(self.occurrences_neg, file)

    @staticmethod
    def count_words_in_reviews(data_path, hm_reviews):
        cnt = Counter()
        neutrals = ['the', 'a', 'and', 'of', 'to', 'is', 'in', 'this', 'it', 'br', '</br', '<br', 'that', 'an',
                    'you', 'on', 'with', 'just', 'but', 'have', 'for', 'movie', 'at', 'about', 'why', 'effect', 'end',
                    'i', 'was', 'my', 'what', 'had', 'who', 'its', 'me', 'be', 'do', 'make', 'all', 'when', 'into', '',
                    'im', 'him', 'then', 'are', 'does']
        if os.path.isdir(data_path):
            for file in os.listdir(data_path)[:hm_reviews]:
                with open(f'{data_path}/{file}', 'r') as review:
                    words = review.read().lower().split(' ')
                for word in words:
                    word = ''.join(e for e in word if e.isalnum())
                    if word not in neutrals:
                        cnt[word] += 1
        else:
            with open(f'{data_path}', 'r') as review:
                words = review.read().lower().split(' ')
            for word in words:
                word = ''.join(e for e in word if e.isalnum())
                if word not in neutrals:
                    cnt[word] += 1
        return cnt

    # compute prob of each word occurring in pos/neg datasets
    @staticmethod
    def prob_all_features_given_label(new_review_words, occurrences_pos, data_len):
        small_num = 1

        prob_list = []
        for new_word in new_review_words:
            prob_list.append((occurrences_pos[new_word] + small_num) / data_len)
        prob_list_log = [round(log2(p), 6) for p in prob_list]
        prob_being_in_review = sum(prob_list_log)
        return prob_being_in_review

    def predict(self, review):
        new_review_words = self.count_words_in_reviews(review, hm_reviews=1)
        data_len = len(self.occurrences_pos) + len(self.occurrences_neg)
        prob_pos = self.prob_all_features_given_label(new_review_words, self.occurrences_pos, data_len)
        prob_neg = self.prob_all_features_given_label(new_review_words, self.occurrences_neg, data_len)
        if max(prob_pos, prob_neg) == prob_pos:
            return 'pos'
        else:
            return 'neg'

    def test(self):
        correct = 0
        hm_samples = 200
        new_pos_reviews = os.listdir(self.pos_test_folder)
        new_neg_reviews = os.listdir(self.neg_test_folder)
        for review in new_pos_reviews[:hm_samples]:
            if self.predict(f'{self.pos_test_folder}/{review}') == 'pos':
                correct += 1
        for review in new_neg_reviews[:hm_samples]:
            if self.predict(f'{self.neg_test_folder}/{review}') == 'neg':
                correct += 1
        print('Accuracy: ', correct / (hm_samples * 2))


reviewClassifier = ReviewClassifier('test_train_folder/aclImdb/train/pos',
                                    'test_train_folder/aclImdb/train/neg',
                                    'test_train_folder/aclImdb/test/pos',
                                    'test_train_folder/aclImdb/test/neg',
                                    'test_train_folder/computed_probs', refresh=False)
reviewClassifier.test()
