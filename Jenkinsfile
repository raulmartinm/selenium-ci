node {
	stage ('SCM checkout'){
		git "https://github.com/raulmartinm/selenium-ci"
	}
	stage ('Build'){
	    sh "./gradlew clean build -x test"
	}
  stage ('Test'){
	    sh "./gradlew test -Penv=jenkins"
	}
}
