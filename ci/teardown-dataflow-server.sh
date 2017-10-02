#!/bin/bash

set -eu

CF_API=https://api.run.pivotal.io

cf api ${CF_API}
cf auth ${CF_USERNAME} ${CF_PASSWORD}
cf target -o ${CF_ORG} -s ${CF_SPACE}

cf delete -f dataflow-server
cf delete-service -f mysql
cf delete-service -f redis
cf delete-service -f rabbit
cf delete-orphaned-routes -f