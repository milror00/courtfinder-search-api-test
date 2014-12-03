@todo
Feature: Search by address or name of a court

Background:
          
          Given I am using the courtfinder api 

Scenario:  Search by name of court


         When I search name or address "Cambridge County Court and Family Court" of a court  
         Then the number of courts returned will be 1  
         Then the court details will be:
         |path                        |value                                                   |type  |
         |[0].name                    |Cambridge County Court and Family Court                 |string|
         |[0].address.town            |Cambridge                                               |string|
         |[0].address.address_lines[0]|Cambridge County Court and Family Court Hearing Centre  |string|
         |[0].address.address_lines[1]|197 East Road                                           |string|
         |[0].lat                     |52.2037125926829                                        |double|
         |[0].lon                     |0.132065389149123                                       |double| 