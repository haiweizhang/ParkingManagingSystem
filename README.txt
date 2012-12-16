任务分解

                Given                           |                  When                |                Then               
--------------------------------------------------------------------------------------------------------------------------------------------------
One Car, One Empty parkinglot with 100 available parking places           | Park that car |  number of available parking place reduce 1, parking succeed.
One Car, One Parkinglot that parked 100 cars                 | Park that car | number of available parking place doesn't change, parking failed.
One parkinglot with 100 available parking places         | Park two cars and fetch two cars | number of available parking places increase 2.
One parkinglot with 100 available parking places         | Park two cars and fetch the first one | Fetched car is exactly the first one.
One parkinglot with 100 available parking places         | Park one car and fetch a car that doesn't exist in the parkinglot | Fethed no car.
One parkinglot with 100 available parking places         | Park one car and fetch that car twice | the first fetching succeed, while the second fetching got no car.




