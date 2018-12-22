from collections import Counter

import bs4 as bs
import datetime as dt
import os
import pandas_datareader.data as web
import pickle
import requests
import pandas as pd
import matplotlib.pyplot as plt
from matplotlib import style
import numpy as np
from sklearn import svm, neighbors, model_selection as cross_validation
from sklearn.ensemble import VotingClassifier, RandomForestClassifier

style.use('ggplot')

sp_500_names_pickle = 'sp_500_names.pickle'
sp500_dir = 'sp_500_prices_dir'
main_df_csv = 'main_df.csv'
hm_days = 7


def save_sp500_tickers():
    resp = requests.get('http://en.wikipedia.org/wiki/List_of_S%26P_500_companies')
    soup = bs.BeautifulSoup(resp.text, 'lxml')
    table = soup.find('table', {'class': 'wikitable sortable'})
    tickers = []

    for row in table.findAll('tr')[1:31]:
        ticker = row.findAll('td')[0].text
        tickers.append(ticker)

    with open(sp_500_names_pickle, 'wb') as f:
        pickle.dump(tickers, f)
    return tickers


def get_sp_500_data_from_yahoo(reload_500=False):
    if reload_500:
        tickers = save_sp500_tickers()
    else:
        with open(sp_500_names_pickle, 'rb') as f:
            tickers = pickle.load(f)
    start = dt.datetime(2000, 1, 1)
    end = dt.datetime.now()
    for ticker in tickers:
        if not os.path.exists(sp500_dir):
            print('creating directory')
            os.mkdir(sp500_dir)
        # noinspection PyBroadException
        try:
            if not os.path.exists(f'{sp500_dir}/{ticker}'):
                print('yayyyy python works !')
                df = web.DataReader(f'{ticker}', 'yahoo', start, end)
                df.to_csv(f'{sp500_dir}/{ticker}')
            else:
                print(f'company name {sp500_dir}/{ticker} already exists')
        except:
            print('exception')


def create_table_with_sp_500_data():
    if not os.path.exists(sp_500_names_pickle):
        get_sp_500_data_from_yahoo()

    with open(sp_500_names_pickle, 'rb') as f:
        tickers = pickle.load(f)

    main_df = pd.DataFrame()
    for counter, ticker in enumerate(tickers):
        df = pd.read_csv(f'{sp500_dir}/{ticker}')
        df.set_index('Date', inplace=True)
        df.drop(['High', 'Low', 'Open', 'Close', 'Volume'], inplace=True, axis=1)
        df.rename(columns={'Adj Close': ticker}, inplace=True)
        if main_df.empty:
            main_df = df
        else:
            main_df = main_df.join(df, how='outer')

    main_df.to_csv(main_df_csv)
    return main_df


def visualize_sp_500():
    if os.path.exists(f'{main_df_csv}'):
        main_df = pd.read_csv(main_df_csv)
    else:
        main_df = create_table_with_sp_500_data()

    df_corr = main_df.corr()
    df_corr.to_csv('sp500corr.csv')

    data1 = df_corr.values
    fig1 = plt.figure()
    ax1 = fig1.add_subplot(111)

    heat_map1 = ax1.pcolor(data1, cmap=plt.cm.RdYlGn)
    fig1.colorbar(heat_map1)

    ax1.set_xticks(np.arange(data1.shape[1]) + 0.5, minor=False)
    ax1.set_yticks(np.arange(data1.shape[0]) + 0.5, minor=False)
    ax1.invert_yaxis()
    ax1.xaxis.tick_top()
    column_labels = df_corr.columns
    row_labels = df_corr.index
    ax1.set_xticklabels(column_labels)
    ax1.set_yticklabels(row_labels)
    plt.xticks(rotation=90)
    heat_map1.set_clim(-1, 1)
    plt.tight_layout()
    # plt.savefig("correlations.png", dpi = (300))
    plt.show()


def process_data_for_labels(ticker):
    if os.path.exists(f'{main_df_csv}'):
        main_df = pd.read_csv(main_df_csv)
    else:
        main_df = create_table_with_sp_500_data()

    main_df.fillna(0, inplace=True)

    for i in range(1, hm_days + 1):
        main_df[f'{ticker}_{i}d'] = (main_df[ticker].shift(-i) - main_df[ticker]) / main_df[ticker]

    main_df.fillna(0, inplace=True)
    return main_df


# can have major improvements
def buy_sell_hold(*args):
    cols = [c for c in args]
    requirement = 0.02
    for col in cols:
        if col > requirement:
            return 1
        if col < -requirement:
            return -1
    return 0


def extract_featuresets(ticker):
    df = process_data_for_labels(ticker)
    with open(sp_500_names_pickle, 'rb') as f:
        tickers = pickle.load(f)

    df[f'{ticker}_target'] = list(map(buy_sell_hold, *[df[f'{ticker}_{i}d'] for i in range(1, hm_days + 1)]))
    vals = df[f'{ticker}_target'].values.tolist()
    print('Data spread:', Counter(vals))
    df.fillna(0, inplace=True)
    df = df.replace([np.inf, -np.inf], np.nan)
    df.dropna(inplace=True)

    df_vals = df[[ticker for ticker in tickers]].pct_change()
    df_vals = df_vals.replace([np.inf, -np.inf], 0)
    df_vals.fillna(0, inplace=True)

    x = df_vals.values
    y = df[f'{ticker}_target'].values
    return x, y, df


def do_ml(ticker):
    x, y, df = extract_featuresets(ticker)

    x_train, x_test, y_train, y_test = cross_validation.train_test_split(x, y, test_size=0.25)

    clf = VotingClassifier([('lsvc', svm.LinearSVC()),
                            ('knn', neighbors.KNeighborsClassifier()),
                            ('rfor', RandomForestClassifier())])

    clf.fit(x_train, y_train)
    confidence = clf.score(x_test, y_test)
    print('accuracy:', confidence)
    predictions = clf.predict(x_test)
    print('predicted class counts:', Counter(predictions))
    return confidence


# examples of running:
do_ml('AMZN')
