#!/bin/bash

set -eu

curl -O http://repo.spring.io/release/org/springframework/cloud/spring-cloud-dataflow-server-cloudfoundry/1.2.4.RELEASE/spring-cloud-dataflow-server-cloudfoundry-1.2.4.RELEASE.jar

CF_API=https://api.run.pivotal.io

cf api ${CF_API}
cf auth ${CF_USERNAME} ${CF_PASSWORD}
cf target -o ${CF_ORG} -s ${CF_SPACE}

cf push dataflow-server -p spring-cloud-dataflow-server-cloudfoundry-1.2.4.RELEASE.jar --no-start -n dataflow-fil -m 1G

rm spring-cloud-dataflow-server-cloudfoundry-1.2.4.RELEASE.jar

cf create-service cleardb boost mysql
cf create-service rediscloud 30mb redis
cf create-service cloudamqp lemur rabbit

cf bind-service dataflow-server mysql
cf bind-service dataflow-server redis
cf bind-service dataflow-server rabbit

cf set-env dataflow-server SPRING_CLOUD_DEPLOYER_CLOUDFOUNDRY_ORG ${CF_ORG}
cf set-env dataflow-server SPRING_CLOUD_DEPLOYER_CLOUDFOUNDRY_SPACE ${CF_SPACE}
cf set-env dataflow-server SPRING_CLOUD_DEPLOYER_CLOUDFOUNDRY_TASK_SERVICES mysql
cf set-env dataflow-server SPRING_CLOUD_DEPLOYER_CLOUDFOUNDRY_USERNAME ${CF_USERNAME}
cf set-env dataflow-server SPRING_CLOUD_DEPLOYER_CLOUDFOUNDRY_PASSWORD ${CF_PASSWORD}
cf set-env dataflow-server SPRING_CLOUD_DEPLOYER_CLOUDFOUNDRY_URL ${CF_API}
cf set-env dataflow-server SPRING_APPLICATION_JSON '{"maven": { "remote-repositories": { "repo1": { "url": "https://jitpack.io" }, "repo2": { "url": "https://repo1.maven.org/maven2/" } } } }'

cf start dataflow-server