def ends_with_vowel(name):
    vowels = ['a', 'e', 'i', 'o', 'u']
    if name[-1:].lower() in vowels:
        return True
    return False


def get_p_vowel_given_gender(names_file):
    with open(names_file, 'r') as names:
        names = names.read().split('\n')
    end_vowel_count = 0
    total_number_of_names = len(names)

    for name in names:
        if ends_with_vowel(name):
            end_vowel_count += 1
    return end_vowel_count / total_number_of_names, len(names)


def predict_gender(name):
    p_vowel_given_male, male_count = get_p_vowel_given_gender('test_train_folder/male_names')
    p_vowel_given_female, female_count = get_p_vowel_given_gender('test_train_folder/female_names')

    p_male = male_count / (male_count + female_count)
    p_female = female_count / (male_count + female_count)

    vowel_count = 5
    letter_count = 26
    evidence = vowel_count / letter_count

    p_of_female_given_ends_vowel = p_female * p_vowel_given_female / evidence
    p_of_male_given_ends_not_vowel = p_male * p_vowel_given_male / evidence

    if ends_with_vowel(name) and p_of_female_given_ends_vowel > p_of_male_given_ends_not_vowel:
        return 'f'
    return 'm'


def test():
    with open('test_train_folder/test_data_females', 'r') as males:
        names = males.read().split('\n')
        correct = 0
        for name in names:
            if predict_gender(name) == 'f':
                correct += 1
            else:
                print('wrong ', name)
        print('accuracy = ', correct / len(names))
