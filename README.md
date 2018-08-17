# storage
this is a library that handles the different ways of storing data in android.<br/><br/>

currently it makes the following methods a whole lot simpler:<br/><br/>
1.storing and retrieving from the sharedPreferences<br/>
2.storing and retrieving from Sqlite Database<br/>
3.storing and retrieving Plain Old Java Objects directly into Sqlite Database<br/><br/>

## SharedPreferences
to store and retrieve data from sharedPreferences, simply create a PreferenceHandler object and call its method. there
is no need to call commit() or apply() when inserting values because that is already handled. 
the SharedPreferences are always instantiated with MODE_PRIVATE. <br/>

## SQLite Database
storing and retrieving data from an sqlite database has been made a whole lot simpler. simply create a DatabaseHandler
class and easily perform CRUD actions with the methods it offers <br/>

## Usage

add jitpack to your project dependency
```
repositories {
     maven { url='https://jitpack.io'}
}    
```

add the library to your module dependency
```
dependencies {
    implementation 'com.github.mundia416:storage:{LATEST_RELEASE}'
}
```


## Store a POJO
you can store a Plain Old Java Object directly into an sqlite database.
```
                Pojo(context).insert(key,pojoObject)

```

## Retrieve a POJO
you simply use the key to retrieve the POJO from the sqlite database
```
get(key2,Array<Foo2>::class.java)
```

## store an array of POJOs
you can store an array or list of POJO objects
```
fun storeArray(myArray:  List<String>){
        Pojo(context).insertOrUpdate(key,myArray)   
 }
  ```

## Retrieve an array of POJOs
you can retrieve an array or list of Pojo objects
```
fun retrieveArray(): List<String>{
        val myArray = Pojo(this).get(key,Array<String>::class.java)
        return myArray.asList()
}
```

## Author

Mundia Mundia 



## License

Copyright 2018 Mundia Mundia

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


