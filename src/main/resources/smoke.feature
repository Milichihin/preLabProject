Feature: Smoke
  As a user
  I check most of main elements on the site
  For sure that functionality of site is all right


  Scenario Outline: Check that a main article equals test article
    Given User opens HomePage
    And User Clicks on News
    And User checks compliance the name of the headline article to '<testedHeadlineArticle>' article

    Examples:
      | testedHeadlineArticle                             |
      | Tonga says tsunami was \'unprecedented disaster\' |


  Scenario: Check that secondary article titles equals test titles
    Given User opens HomePage
    And User Clicks on News
    And Checks secondary article titles



  Scenario: Compare category name to search result
    Given User opens HomePage
    And User Clicks on News
    And User found a category name of headline article
    And User makes search by name of headline article category
    And Check the name of the first article against a name of headline article



  Scenario Outline: Verify for empty Field
    Given User opens HomePage
    And User Clicks on News
    And User closes modal window if it showing
    And User clicks on `Coronavirus`
    Then User clicks on `Your Coronavirus Stories`
    And User goes to `Coronavirus: Send us your questions`
    Then User fills the '<emptyData>' data into the one '<field>' and '<correctData>' data into the others
    Then User checks does the form react correctly to errors

    Examples:
      | emptyData  | correctData  | field    |
      |            | qwerty1      | question |
      |            | qwerty2      | email    |
      |            | qwerty3      | name     |

