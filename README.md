This code is written as my submission for the second assignment of the course TEPR60 (Test automation and programming) of the Software tester programme at EC utbildning.

The purpose of the code is to test registration of a user on the site Basketball England through automation using Cucumber and Selenium.

There are four required scenarios for the assignment:
  - Everything goes as expected and a user is created
  - Unsuccessful registration due to missing last name
  - Unsuccessful registration due to password inputs not matching
  - Unsuccessful registration due to not agreeing to terms and conditions

To these I have added two other scenarios:
  - Unsuccessful registration due to not agreeing to terms and conditions, code of ethics and conduct, and age/parental requirement
  - Unsuccessful registration due to missing last name, password inputs not matching, and not agreeing to terms and conditions

These extra scenarios focus on when there is more then one reason for a failed registration, and the first one also, ensures that when none of the checkboxes are to be checked it is handled as intended.

There is the following requirements on the test cases:
  - Verification should be done with at least one Junit assert per scenario:
  - The feature file should be created with BDD and have a clear structure
  - The feature file should be linked to Selenium code that performs the testing
  - The test cases should be executing via at least one Scenario Outline
  - The test cases should be executed on at least two browsers
  - Create at least one private method that uses explicit wait

