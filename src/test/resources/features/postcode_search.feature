
Feature: As a civilian I need to enter my postcode to find the closest court dealing with adoption so I can send in my application

Background:
          
          Given I am using the courtfinder api 

Scenario:  Search by postcode and locate my closest courts for Adoption


         When I search by postcode "sg80lt" and area of law "Adoption"   
         Then the number of courts returned will be 2   
         Then the court details will be:
         |path                        |value                                                   |type  |
         |[0].name                    |Cambridge County Court and Family Court                 |string|
         |[0].address.town            |Cambridge                                               |string|
         |[0].address.address_lines[0]|Cambridge County Court and Family Court Hearing Centre  |string|
         |[0].address.address_lines[1]|197 East Road                                           |string|
         |[0].lat                     |52.2037125926829                                        |double|
         |[0].lon                     |0.132065389149123                                       |double|
         |[1].name                    |Peterborough Combined Court Centre                      |string|
         |[1].address.town            |Peterborough                                            |string|
         |[1].address.address_lines[0]|Peterborough Combined Court and Family Hearing Centre   |string|
         |[1].address.address_lines[1]|Crown Buildings                                         |string|
         |[1].lat                     |52.5700659339344                                        |double|
         |[1].lon                     |-0.240219616751609                                      |double|  
         
Scenario:  Search by postcode and locate my closest court for Children


         When I search by postcode "cm233fe" and area of law "Children"   
         Then the number of courts returned will be 1   
         Then the court details will be:
         |path                        |value                                                   |type  |
         |[0].name                    |Watford County Court and Family Court                   |string|
         |[0].address.town            |Watford                                                 |string|
         |[0].address.address_lines[0]|Watford County Court and Family Court Hearing Centre    |string|
         |[0].address.address_lines[1]|3rd Floor                                               |string|
         |[0].lat                     |51.663675400498                                         |double|
         |[0].lon                     |-0.398796389317627                                      |double|       
         
    