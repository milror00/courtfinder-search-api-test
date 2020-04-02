@done 
Feature: Search by address or name of a court


Scenario:  Search by name of court


         Given I search name or address "Central Criminal Court" of a court  
         Then the response code is 200
         And the number of courts returned will be 1  
         And the court details will be:
         |path                        |value                                                    |type  |
         |[0].name                    |Central Criminal Court                                   |string|
         |[0].address.town            |London                                                   |string|
         |[0].address.address_lines[0]| Old Bailey                                              |string|
         |[0].lat                     |51.5154913731663                                         |double|
         |[0].lon                     |-0.101997218982794                                       |double|

      
Scenario Outline: Complete court name returns individual court entry in the results page

         Given I search name or address "<courtname>" of a court  
         Then the response code is 200
         And the number of courts returned will be 1
         And the value for the response path "<path>" of "<type>" is "<courtname>"
         Examples:     
         |courtname                         |type  |path        |
         |Central Criminal Court            |string|[0].name    |      
         |accrington county court           |string|[0].name    |
         |Bournemouth Crown court           |string|[0].name    | 
         


            
Scenario: Sort order will be venue,town,street,county - sub sort is on areas of law

         Given I search name or address "Derby" of a court 
         Then the response code is 200 
         And the court details will be:
         |path                        |court                                                   |type  |
         |[0].name                    |Derby Combined Court Centre                             |string|
         |[1].name                    |Derby Magistrates' Court                                |string|
         |[2].name                    |Derby Social Security and Child Support Tribunal        |string|  
         
                    
Scenario: Sort order will be venue,town,street,county - sub sort is on areas of law

         Given I search name or address "Durham" of a court  
         Then the response code is 200
         And the court details will be:
         |path                        |court                                                                       |type  |
         |[0].name                    |Durham County Court and Family Court                                        |string|
         |[1].name                    |Cleveland, Durham and Northumbria Regional Divorce Centre                  |string|                           
         |[2].name                    |Durham Crown Court                                                          |string|   
         |[3].name                    |Durham Magistrates' Court                                                   |string|
         |[4].name                    |Cleveland, Durham, Northumbria and North Yorkshire Central Enforcement Unit |string|           