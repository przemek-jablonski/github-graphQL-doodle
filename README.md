# github-graphQL-doodle
<sup>last commit on Nov 8, 2016</sup>

Doodling with new GitHub's GraphQL API for querying and retrieving data.
#

Deserialization of a API data is done with Gson, while serialization of graph queries is done with custom parser, fully compliant with GraphQL java implementation, regardless of graph nesting levels. 

GraphQL Converter factory can be found in _backend/serializers/_ package.

GraphQL queries can be found in _backend/models/graphql/queries/_ package.

Other than that, it's very simple and standard modern MVP Android app.
#

>//todo: Right now Graphql query and response objects are decoupled from one another. I will be finishing reflective query creation, based on response object in near future.

#

```
BRO TIP: github's api key is stored in apikeys.xml file in res/values folder, which is not in this repo! Create it, like below

<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="github_graphql_app_personal_token">YOUR_API_KEY_HERE</string>
</resources>
```
https://help.github.com/articles/creating-an-access-token-for-command-line-use/

#


Used Dagger2, Retrofit, GraphQL-Java, Gson, MVP, ButterKnife, Picasso.


<img src="screens/Screenshot_20161107-194400.png?raw=true" width="500">



![](screens/Screenshot_20161107-195042.png?raw=true )



![](screens/Screenshot_20161107-194500.png?raw=true )



![](screens/Screenshot_20161107-204224.png?raw=true )
