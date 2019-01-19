# Bayes theorem example

# days = [["ran", "was tired"], ["ran", "was not tired"], ["didn"'t run", "was tired"], ["ran", "was tired"],
#         ["didn't run", "was not tired"], ["ran", "was not tired"], ["ran", "was tired"]]
#
# # P(Tired | Run = true) - ?
# # P(A) = P(Tired)
# # P(B) = P(Ran)
# # P(B | A) = P(Ran | Tired = true)
#
# p_tired = len([i for i in days if i[1] == "was tired"]) / len(days)
# p_ran = len([i for i in days if i[0] == "ran"]) / len(days)
# p_ran_given_tired = len([i for i in days if i == ["ran", "was tired"]]) /
#  len([d for d in days if d[1] == "was tired"])
#
# p_tired_given_ran = (p_ran_given_tired * p_tired) / p_ran
# print("Probability of being tired given that you ran", p_tired_given_ran)

# Naive Bayes theorem

days = [["ran", "was tired", "woke up early"], ["ran", "was not tired", "didn't wake up early"],
        ["didn't run", "was tired", "woke up early"], ["ran", "was tired", "didn't wake up early"],
        ["didn't run", "was tired", "woke up early"], ["ran", "was not tired", "didn't wake up early"],
        ["ran", "was tired", "woke up early"]]

# We're trying to predict whether or not the person was tired on this day.
new_day = ["ran", "didn't wake up early"]
# P(Y | x1,x2..xn) - ?

# P(Y) = P(Tired)
# Prod (P(x1) | Y = True), (P(x2) | Y = True), (P(xn) | Y = True) =
# = P(Ran | Tired = True) * P(didn't wake up early | Tired = True)
# P(x1,x2...xn) = P(Ran) * P(Didn't wake up early)

days_len = len(days)
p_tired = len([d for d in days if d[1] == "was tired"]) / days_len
p_not_tired = len([d for d in days if d[1] == "was not tired"]) / days_len

p_ran_and_did_not_wake_up_early = len([d for d in days if (d[0] == new_day[0] and d[2] == new_day[1])]) / days_len


def prob_all_features_given_label(dataset, new_feature, label):
    return len([d for d in dataset if d[0] == new_feature[0] and d[1] == label]) / len(dataset) * \
           len([d for d in dataset if d[2] == new_feature[1] and d[1] == label]) / len(dataset)


prob_features_given_tired = prob_all_features_given_label(days, new_day, "was tired")
prob_features_given_not_tired = prob_all_features_given_label(days, new_day, "was not tired")

prob_tired = (p_tired * prob_features_given_tired) / p_ran_and_did_not_wake_up_early
prob_not_tired = (p_not_tired * prob_features_given_not_tired) / p_ran_and_did_not_wake_up_early

print(f"Probability given that you {new_day[0]} and {new_day[1]}.Tired: {prob_tired}, Not tired: {prob_not_tired}")
# TODO baad way of getting result
print("Prediction:", max({'Was tired': prob_tired, 'Was not tired': prob_not_tired}))
