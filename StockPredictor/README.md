# Stock Predictor
The project uses Recurrent Neural Networks to predict stock prices for a given company (Tesla in this case) using daily adjusted close price, volume of trade. Initially I planned to use the amount of likes the company's page got on twitter but there was no correlation with the price (see the below image). For the future I am going to analyse the sentiment from twitter users talking about the company (whether the tweet is positive or negative) and see how it correlates with the daily price. 
With the little data I had the model predicts with **~84% accuracy** whether to buy, sell or hold the stock.

### Correlation between likes on twitter and the adjusted close price for Tesla's stock

![demo](/home/vahan/Desktop/twitter-price-correlation.png)

# Getting started
Clone the project
## Installings
Install python 3.6 version or later using
```sh
$ sudo add-apt-repository ppa:jonathonf/python-3.6
```
```sh
$ sudo apt-get update
```
```sh
$ sudo apt-get install python3.6
```
 
Install 'pip' for python 3.6 using
```sh
$ curl https://bootstrap.pypa.io/get-pip.py | sudo -H python3.6z
```
Install dependencies from 'requirements.txt' file using 
```sh
$ pip install -r StockPredictor/requirements.txt
```


# Usage
- Run the 'predict_prices.py' file with python3.6 to train the model and plot the results. 
- Run 'twitter_analysis.py' to get the amount of daily likes for a given company
- Run 'stock_prices.py' to get the stock prices of a company from yahoo.

# Author
- ##### Vahan Hovhannisyan
