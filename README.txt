Given                When	Then
Parking lot		
One car, one Empty parking lot with 100 available parking places           	Park that car 	Number of available parking place reduce 1, parking succeed.
One car, one parking lot that parked 100 cars                 	Park that car 	Number of available parking place doesn't change, parking failed.
One parking lot with 100 available parking places         	Park two cars and fetch two cars 	Number of available parking places increase 2.
One parking lot with 100 available parking places         	Park two cars and fetch the first one 	Fetch car is exactly the first one.
One parking lot with 100 available parking places         	Park one car and fetch a car that doesn't exist in the parking lot 	Fetch no car.
One parking lot with 100 available parking places         	 Park one car and fetch that car twice 	The first fetching succeed, while the second fetching got no car.
Parking boy		
One parking boy who manages 3 parking lot	Park a car when no parking lot are full	Park that car in the first parking lot
One parking boy who manages 3 parking lot	Park a car when first parking lot is full	Park that car in the second parking lot
One parking boy who manages 3 parking lot	Park a car when first two parking lots are full	Park that car in the third parking lot
One parking boy who manages 3 parking lot	Park a car when all parking lots are full	Parking fails
One parking boy who manages 3 parking lot	Park one car in the first parking lot and Fetch the car	Fetched exactly the same car parked.
One parking boy who manages 3 parking lot	Park one car in the third parking lot , fetch another car from the first parking lot, then fetch that car parked in the third parking lot	Fetched exactly the same car parked in the third parking lot
