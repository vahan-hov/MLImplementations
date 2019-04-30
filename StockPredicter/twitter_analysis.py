import os
import pandas as pd
from tweepy import OAuthHandler, API
from matplotlib import pyplot as plt, style
from datetime import datetime, timedelta, timezone, date
from pandas.plotting import register_matplotlib_converters
from utils.common_variables import tweets_data_path, dates_column, likes_column
from utils.credentials import api_key, api_secret_key, access_token, access_secret_token


# -------------------------------------------------------------------------------
# Function - To plot the amount of likes a company had on a daily basis
# -------------------------------------------------------------------------------
def graph_data(favorites, dates, labels):
    register_matplotlib_converters()

    fig, ax = plt.subplots()
    plt.plot(dates, favorites)

    fig.autofmt_xdate()
    ax.set_title('Popularity of Tweets')
    plt.xlabel('date')
    plt.ylabel('likes')
    plt.legend(labels)
    style.use('fivethirtyeight')
    plt.show()


# -------------------------------------------------------------------------------
# Function - To convert a string object to date object
# -------------------------------------------------------------------------------
def convert_to_date(string_date):
    date_format = '%Y-%m-%d %H:%M:%S'
    return datetime.strptime(string_date.split('+')[0], date_format)


# -------------------------------------------------------------------------------
# Function - To check if tweets date is in the specified time period
# -------------------------------------------------------------------------------
def is_within_time_range(tweet_date, hm_days, verbose=True):
    hm_days_ago = datetime.now(timezone.utc) - timedelta(days=hm_days)
    tweet_date = utc_to_local(tweet_date)
    if verbose:
        print(f'comparing tweet date {tweet_date} with {hm_days} ago {hm_days_ago}\n')
    return hm_days_ago < tweet_date


# -------------------------------------------------------------------------------
# Function - To convert date to local time
# -------------------------------------------------------------------------------
def utc_to_local(utc_dt):
    return utc_dt.replace(tzinfo=timezone.utc).astimezone(tz=None)


# -------------------------------------------------------------------------------
# Function - To save the tweets
# -------------------------------------------------------------------------------
def save_as_pickle(data):
    data.to_pickle(tweets_data_path)
    print(f'saved data in {tweets_data_path}')


# -------------------------------------------------------------------------------
# Function - To aggregate duplicate dates into one summing up the corresponding likes
# -------------------------------------------------------------------------------
def preprocess_data(df):
    df = df.groupby(dates_column).agg({dates_column: 'first',
                                       likes_column: 'sum'}, inplace=True)
    df.drop(dates_column, axis=1, inplace=True)
    print('preprocessed data')
    return df


# -------------------------------------------------------------------------------
# Function - To pull tweets using 'tweepy' APIs
# -------------------------------------------------------------------------------
def pull_data_from_twitter(api, query, item_count, hm_days, verbose=False):
    # pull data
    all_data = api.user_timeline(screen_name=query, count=item_count)

    dates = []
    favorites = []
    reached_time_limit = False
    for json_data in all_data:

        date_published = json_data.created_at
        date_published = utc_to_local(date_published)

        # check if the date is within the specified range
        if not is_within_time_range(date_published, hm_days, verbose=verbose):
            reached_time_limit = True
            break

        date_published = date_published.date()

        dates.append(date_published)
        favorites.append(json_data.favorite_count)

    # put data into pandas dataframe
    tweets_df = pd.DataFrame()
    tweets_df[dates_column] = dates
    tweets_df[likes_column] = favorites

    if verbose:
        print(tweets_df)

    if reached_time_limit:
        print('reached time limit')
    else:
        print('did not reach the specified time limit')

    return tweets_df


# -------------------------------------------------------------------------------
# Function - To authenticate to twitter account
# -------------------------------------------------------------------------------
def handle_auth():
    auth = OAuthHandler(api_key, api_secret_key)
    auth.set_access_token(access_token, access_secret_token)
    return API(auth)


# -------------------------------------------------------------------------------
# Function - To get the amount of days since the given date
# -------------------------------------------------------------------------------
def hm_days_passed_since(start_date):
    split_date = start_date.split('-')
    y, m, d = int(split_date[0]), int(split_date[1]), int(split_date[2])
    d0 = date(y, m, d)
    d1 = date.today()
    delta = d1 - d0
    print(f'time delta: {delta.days} days')
    return delta.days


# -------------------------------------------------------------------------------
# Function - To return preprocessed tweets
# -------------------------------------------------------------------------------
def get_preprocessed_tweets(start_date=None, item_count=None, query=None, refresh=False):
    if not refresh and os.path.exists(tweets_data_path):
        return pd.read_pickle(tweets_data_path)

    hm_days = hm_days_passed_since(start_date)

    api = handle_auth()
    tweet_data = pull_data_from_twitter(api,
                                        query=query,
                                        item_count=item_count,
                                        hm_days=hm_days,
                                        verbose=True)
    prep_data = preprocess_data(tweet_data)
    save_as_pickle(prep_data)
    return prep_data


# -------------------------------------------------------------------------------
# Main Function
# -------------------------------------------------------------------------------
def main():
    query = '@Tesla'
    item_count = 1000
    start_date = '2019-01-01'

    tweet_df = get_preprocessed_tweets(start_date=start_date, item_count=item_count, query=query, refresh=False)
    dates = tweet_df.index
    graph_data(tweet_df[likes_column], dates, labels=query[1:])


if __name__ == '__main__':
    main()
