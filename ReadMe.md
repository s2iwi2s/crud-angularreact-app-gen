# Url = http://localhost:8081/

* Springboot 2.3.3.RELEASE
* angular/core=7.2.6
* react=16.13.1
* JWT - in memory
* Cors mapping to http://localhost:3000

### NOTE
- This code is not complete. There are error's you need to manually fix
- THIS IS CONFIGURED: spring.jpa.hibernate.ddl-auto=create-drop
- It means that it will drop and create new tables.
- mvn install:install-file –Dfile=ojdbc6.jar -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.2.0 -Dpackaging=jar

### Start Windows Services
```sh
net start OracleServiceXE
net start MySQL80
```

### Maven Commands
```sh
cls && mvn clean test
cls && mvn clean package
cls && mvn clean spring-boot:run
```

### Start Client Server
```sh
ng serve -o
```

### Generate Crude Components
* Before running the main class, configure CrudeGen_java.json/CrudeGen_react.json/CrudeGen_angular.json.
* Main class is CrudeGen2.java and will read json file CrudeGen_java.json/CrudeGen_react.json/CrudeGen_angular.json
* After running the main class, you need to add the new component in 'Crud-angular-tmpl/client/src/app/app.module.ts' and 'Crud-angular-tmpl/client/src/app/app-routing.module.ts'

### Sample Object from CrudeGen_java.json/CrudeGen_react.json/CrudeGen_angular.json
```
	"OBJECTS": [{
		"NAME": "MyCases",
		"FIELDS": [
			{
				"LABEL": "Title",
				"NAME": "Title",
				"TYPE": "String",
				"MAPPING": "",
				"REQUIRED": "Y"
			},
			{
				"LABEL": "Status",
				"NAME": "Status",
				"TYPE": "CodeGroups",
				"MAPPING": "@ManyToOne",
				"CODE_GROUP": "STATUS",
				"LIST_TYPE": "STATUS",
				"REQUIRED": "Y"
			},
			{
				"LABEL": "Case Type 1",
				"NAME": "CaseType1",
				"TYPE": "CodeGroups",
				"MAPPING": "@ManyToOne",
				"CODE_GROUP": "CASE_TYPE_1",
				"LIST_TYPE": "CASE_TYPE_1",
				"REQUIRED": "Y"
			},
			{
				"LABEL": "Case Type 2",
				"NAME": "CaseType2",
				"TYPE": "CodeGroups",
				"MAPPING": "@ManyToOne",
				"CODE_GROUP": "CASE_TYPE_2",
				"LIST_TYPE": "CASE_TYPE_2",
				"REQUIRED": "Y"
			},
			{
				"LABEL": "Case Type 3",
				"NAME": "CaseType3",
				"TYPE": "CodeGroups",
				"MAPPING": "@ManyToOne",
				"CODE_GROUP": "CASE_TYPE_3",
				"LIST_TYPE": "CASE_TYPE_3",
				"REQUIRED": "Y"
			},
			{
				"LABEL": "Status Code",
				"NAME": "StatusCode",
				"TYPE": "CodeGroups",
				"MAPPING": "@ManyToOne",
				"CODE_GROUP": "STATUS_CODE",
				"LIST_TYPE": "STATUS_CODE"
			},
			{
				"LABEL": "Comments",
				"NAME": "Comments",
				"TYPE": "String",
				"MAPPING": ""
			}
		]
	}
```

### Manual fix
- Add new components in 'Crud-angular-tmpl/client/src/app/app.module.ts' and 'Crud-angular-tmpl/client/src/app/app-routing.module.ts'
- Fix the generated detail component. Fix null error after clicking new button from the list. This error may be the cause if you have Code Groups or List of Values

```
  details: any = {
    status: { id: '' },
    caseType1: { id: '' },
    caseType2: { id: '' },
    caseType3: { id: '' },
    statusCode: { id: '' },
  }
```

### GIT
```
…or create a new repository on the command line
echo "# crud-angularreact-app-gen" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M master
git remote add origin https://github.com/s2iwi2s/crud-angularreact-app-gen.git
git push -u origin master
                
…or push an existing repository from the command line
git remote add origin https://github.com/s2iwi2s/crud-angularreact-app-gen.git
git branch -M master
git push -u origin master
```
