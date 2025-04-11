Feature: Register User

  Scenario: Successful registration
    Given I initiate "Firefox"
    And  I navigate to Create An Account
    When I type "10/04/1997" in the "DateOfBirth" field
    And I type "Joey" in the "Forename" field
    And I type "Doe" in the "Surname" field
    And I type random email in the "EmailAddress" field
    And I type random email in the "ConfirmEmailAddress" field
    And I type "JD-pwd-1004" in the "Password" field
    And I type "JD-pwd-1004" in the "ConfirmPassword" field
    And I check "TermsAccept, AgeAccept, AgreeToCodeOfEthicsAndConduct" boxes
    And I click "CONFIRM AND JOIN"
    Then I verify registration success

  Scenario Outline: Unsuccessful registration
    Given I initiate "<browser>"
    And I navigate to Create An Account
    When I type "<dateOfBirth>" in the "DateOfBirth" field
    And I type "<firstName>" in the "Forename" field
    And I type "<lastName>" in the "Surname" field
    And I type "<email>" in the "EmailAddress" field
    And I type "<cEmail>" in the "ConfirmEmailAddress" field
    And I type "<pwd>" in the "Password" field
    And I type "<cPwd>" in the "ConfirmPassword" field
    And I check "<checkboxes>" boxes
    And I click "CONFIRM AND JOIN"
    Then I expect this "<errorMsg>"

    Examples:
      | browser | dateOfBirth | firstName | lastName    | email                     | cEmail                    | pwd              | cPwd             | checkboxes                                            | errorMsg                                                                                                                                                                                                                                               |
      # last name missing
      | Edge    | 28/04/1960  | Egon      |             | egonsMail@fakemail.com    | egonsMail@fakemail.com    | aPwdToRemember   | aPwdToRemember   | TermsAccept, AgeAccept, AgreeToCodeOfEthicsAndConduct | Last Name is required                                                                                                                                                                                                                                  |
      # password does not match
      | Chrome  | 11/07/1958  | Lisbeth   | Persson     | lis.persson@fakemail.com  | lis.persson@fakemail.com  | L-beth-580711    | P-beth-110758    | TermsAccept, AgeAccept, AgreeToCodeOfEthicsAndConduct | Password did not match                                                                                                                                                                                                                                 |
      # terms and conditions are not checked
      | Firefox | 27/05/1994  | Gabriel   | van Helsing | vanHel@fakemail.com       | vanHel@fakemail.com       | helsing742902    | helsing742902    | AgeAccept, AgreeToCodeOfEthicsAndConduct              | You must confirm that you have read and accepted our Terms and Conditions                                                                                                                                                                              |
      # none of the checkboxes are checked
      | Edge    | 03/11/1991  | Emma      | Henriksson  | Emma.Henriks@fakemail.com | Emma.Henriks@fakemail.com | EmHen941103      | EmHen941103      |                                                       | You must confirm that you have read and accepted our Terms and Conditions\nYou must confirm that you are over 18 or a person with parental responsibility\nYou must confirm that you have read, understood and agree to the Code of Ethics and Conduct |
      # last name missing, password does not match terms and conditions are not checked
      | Chrome  | 27/03/1997  | Johan     |             | johan.wallin@fakemail.com | johan.wallin@fakemail.com | pwd-970327-joWal | joWal-270397-pwd | AgeAccept, AgreeToCodeOfEthicsAndConduct              | Last Name is required\nPassword did not match\nYou must confirm that you have read and accepted our Terms and Conditions                                                                                                                               |
