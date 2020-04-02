@done  
Feature: As a civilian I need to enter my postcode to find the closest court dealing with adoption so I can send in my application 

 
Scenario:  Search by postcode and locate my closest courts for Adoption


         Given I search by postcode "sg80lt" and area of law "Adoption"  
         Then the response code is 200
         And number of courts returned will be 1   
         And the court details will be:
         |path                        |value                                                   |type  |
         |[0].name                    |Peterborough Combined Court Centre                      |string|
         |[0].address.town            |Peterborough                                            |string|
         |[0].address.address_lines[0]|Crown Buildings                                         |string|
         |[0].address.address_lines[1]|Rivergate                                               |string|
         |[0].lat                     |52.5698906045548                                        |double|
         |[0].lon                     |-0.239916760135677                                      |double|  

       
Scenario:  Search by postcode and locate my closest court for Children


         Given I search by postcode "cm233fe" and area of law "Children"   
         Then the response code is 200
         And the number of courts returned will be 1   
         And the court details will be:
         |path                        |value                                                   |type  |
         |[0].name                    |Watford County Court and Family Court                   |string|
         |[0].address.town            |Watford                                                 |string|
         |[0].address.address_lines[0]|3rd Floor                                               |string|
         |[0].address.address_lines[1]|Cassiobury House                                        |string|
         |[0].lat                     |51.663675400498                                         |double|
         |[0].lon                     |-0.398796389317627                                      |double|       
         

Scenario:  Search by invalid postcode

         Given I search by postcode "zzzzzz" and area of law "Children"   
         Then the a response code is 400  
         
 