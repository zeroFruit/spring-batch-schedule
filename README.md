# spring-batch-schedule

### Description
Web application which run spring-batch job. That specific job call external REST api.



```
http://localhost:8080
```


|method|route|parameters|description|
|----|----|----|----|
|GET|/|name=\<job-name\>|Start scheduled job. And return job id|
|DELETE|/:id||Stop job matching with job id|
|PATCH|/:id||Restart job matching with job id|
|GET|/random||Sample REST api which is called inside job|


#### Which REST API is being used

You can test here. https://gturnquist-quoters.cfapps.io/api/random
