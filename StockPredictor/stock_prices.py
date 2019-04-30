import os
import pandas as pd
from yahoo_fin import stock_info as si
from utils.common_variables import adj_close_column, pct_change_column, \
    yest_adj_close_column, target_column, stock_prices_path


# -------------------------------------------------------------------------------
# Function - To save the acquired data
# -------------------------------------------------------------------------------
def save_as_pickle(data):
    data.to_pickle(stock_prices_path)
    print(f'saved stock data in {stock_prices_path}')


# -------------------------------------------------------------------------------
# Function - To preprocess data: drop unnecessary columns and add a target column based on buying/selling thresholds
# -------------------------------------------------------------------------------
def preprocess_data(data):
    buy_thresholds = 0.02
    sell_threshold = -0.02
    data.drop(['open', 'ticker', 'high', 'low', 'close'], inplace=True, axis=1)
    data[yest_adj_close_column] = data[adj_close_column].shift(-1)
    data.dropna(inplace=True)
    data[pct_change_column] = data[adj_close_column] / data[yest_adj_close_column] - 1
    data[target_column] = [1 if change > buy_thresholds else -1 if change < sell_threshold else 0
                           for change in data[pct_change_column]]

    return data


# -------------------------------------------------------------------------------
# Function - To get and preprocess stock prices data from yahoo
# -------------------------------------------------------------------------------
def get_preprocessed_stock_data_from_yahoo(ticker, refresh=False):
    if not refresh and os.path.exists(stock_prices_path):
        return pd.read_pickle(stock_prices_path)

    data = si.get_data(ticker)
    data = preprocess_data(data)

    save_as_pickle(data)

    return data


# -------------------------------------------------------------------------------
# Main Function
# -------------------------------------------------------------------------------
def main():
    ticker = 'TSLA'
    data = get_preprocessed_stock_data_from_yahoo(ticker=ticker, refresh=False)
    print(data)


if __name__ == '__main__':
    main()
