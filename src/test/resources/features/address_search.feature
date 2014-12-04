
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

       
Scenario Outline: Complete court name returns individual court entry in the results page

         When I search name or address "<courtname>" of a court  
         Then the number of courts returned will be 1
         And the value for the response path "<path>" of "<type>" is "<courtname>"
         Examples:     
         |courtname                         |type  |path        |
         |Central Criminal Court            |string|[0].name    |      
         |accrington county court           |string|[0].name    |
         |Bath county Court and Family court|string|[0].name    |
         |Bournemouth Crown court           |string|[0].name    | 
         
    
Scenario Outline: Inactive court should not be displayed

               When I search name or address "<inactivecourt>" of a court 
               Then the number of courts returned will be 0 
               Examples:
               |inactivecourt               |
               |Southport Magistrates' Court| 

                      
Scenario: Sort order will be venue,town,street,county - sub sort is on areas of law

         When I search name or address "Derby" of a court  
         Then the court details will be:
         |path                        |court                                                   |type  |
         |[0].name                    |Derby Combined Court Centre                             |string|
         |[1].name                    |Derby Social Security and Child Support Tribunal        |string|
         |[2].name                    |Derby Magistrates' Court                                |string|  
         
                      
Scenario: Sort order will be venue,town,street,county - sub sort is on areas of law

         When I search name or address "street" of a court  
         Then the court details will be:
         |path                        |court                                                   |type  |
         |[0].name                    |Pocock Street Tribunal Hearing Centre                   |string|
         |[1].name                    |Chester-le-Street Magistrates' Court                    |string|
         |[2].name                    |Durham Magistrates' Court                               |string|                           
         |[3].name                    |Consett Magistrates' Court                              |string|              