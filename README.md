# FinancialTransactionAnalyzer

Calculates Relative balance for the given financial transaction records.

Input :

User is asked to input CSV file path & accountId, from date(dd/MM/yyyy HH:mm:ss) & to date(dd/MM/yyyy HH:mm:ss) should be provided as arguments.

Output will be as below:

Relative balance for the period is: -$25.00
Number of transactions included is: 1 

Design:
- Get list of Transactions for the given account ID only.
- Create a List of transaction Id's which are the related Transactions for the accountID
- Traverse through account ID transactions
   - Ignore REVERSAL Transactions
   - Ignore trasaction ID's which are in Related Transactions List
   - Compute relative balance for the remaining transactions


How To Run:

1. Download or clone the project

2. Go to the project folder

3. Run the following maven commands:

mvn compile

mvn exec:java -Dexec.mainClass="com.codingchallenge.Main" -Dexec.args="ACC334455 '20/10/2018 12:00:00' '20/10/2018 19:00:00'" 
