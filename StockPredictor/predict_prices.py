import os
import numpy as np
import pandas as pd
from utils.common_variables import main_df_path
from matplotlib import pyplot as plt
from sklearn.preprocessing import normalize
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import LSTM, Dense
from twitter_analysis import get_preprocessed_tweets
from stock_prices import get_preprocessed_stock_data_from_yahoo


# -------------------------------------------------------------------------------
# Function - To merge stock prices history and tweets history into one pandas dataframe
# -------------------------------------------------------------------------------
def merge_tweet_stock_dfs(tweets_df, stocks_df):
    main_df = tweets_df.join(other=stocks_df, how='inner')
    main_df.to_pickle(main_df_path)
    print(f'saved main df in {main_df_path}')
    return main_df


# -------------------------------------------------------------------------------
# Function - To get stock prices history and tweets history from data folder
# -------------------------------------------------------------------------------
def get_tweets_and_stocks_data(ticker, refresh=False):
    if not refresh and os.path.exists(main_df_path):
        return pd.read_pickle(main_df_path)

    tweets_df = get_preprocessed_tweets()
    stocks_df = get_preprocessed_stock_data_from_yahoo(ticker)
    main_df = merge_tweet_stock_dfs(tweets_df=tweets_df, stocks_df=stocks_df)
    main_df.dropna(inplace=True)
    return main_df


# -------------------------------------------------------------------------------
# Function - To plot the correlation between twitter daily likes and stock prices on a graph
# -------------------------------------------------------------------------------
def show_correlation_table(df, save=False):
    df_corr = df.corr()
    data = df_corr.values

    fig1 = plt.figure()
    ax = fig1.add_subplot(111)

    heat_map = ax.pcolor(data, cmap=plt.cm.RdYlGn)
    fig1.colorbar(heat_map)

    ax.set_title('Correlation Table')
    ax.invert_yaxis()
    ax.xaxis.tick_top()

    column_labels = df_corr.columns
    row_labels = df_corr.index

    ax.set_xticklabels(column_labels)
    ax.set_yticklabels(row_labels)

    plt.xticks(rotation=90)
    heat_map.set_clim(-1, 1)
    if save:
        plt.savefig("twitter-price-correlation.png", dpi=300)
    plt.show()


# -------------------------------------------------------------------------------
# Function - To plot the accuracy metrics on a graph
# -------------------------------------------------------------------------------
def plot_model(history):
    plt.plot(history.history['loss'], label='train_loss')
    plt.plot(history.history['val_loss'], label='test_loss')
    plt.plot(history.history['acc'], label='train_acc')
    plt.plot(history.history['val_acc'], label='test_acc')
    plt.legend()
    plt.show()


# -------------------------------------------------------------------------------
# Function - To design and train an RNN
# -------------------------------------------------------------------------------
def fit_model(train_xs, val_xs, train_ys, val_ys):
    model = Sequential()
    model.add(LSTM(50, input_shape=(train_xs.shape[1], train_xs.shape[2])))
    model.add(Dense(1))
    model.compile(loss='mae', optimizer='adam', metrics=['accuracy'])
    return model.fit(x=train_xs, y=train_ys, epochs=15, batch_size=10,
                     validation_data=(val_xs, val_ys), verbose=2,
                     shuffle=False)


# -------------------------------------------------------------------------------
# Function - To preprocess data
# -------------------------------------------------------------------------------
def preprocessing(xs, ys, val_split=0.1):
    # rescale features between 0 and 1
    normalize(xs, axis=0, copy=False)

    # reshape to fit keras requirement
    xs = np.reshape(xs, (xs.shape[0], 1, xs.shape[1]))

    # make train and validation splits
    val_size = int(len(xs) * val_split)

    val_xs = xs[:val_size]
    train_xs = xs[val_size:]

    val_ys = ys[:val_size]
    train_ys = ys[val_size:]
    return train_xs, val_xs, train_ys, val_ys


# -------------------------------------------------------------------------------
# Main Function
# -------------------------------------------------------------------------------
def main():
    ticker = 'TSLA'

    main_df = get_preprocessed_stock_data_from_yahoo(ticker)
    xs = main_df[['pct change', 'volume']].to_numpy()
    ys = main_df['target'].to_numpy()

    train_xs, val_xs, train_ys, val_ys = preprocessing(xs, ys)
    history = fit_model(train_xs, val_xs, train_ys, val_ys)
    plot_model(history)


if __name__ == '__main__':
    main()
