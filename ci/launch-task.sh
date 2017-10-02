#!/bin/bash

set -eu

COMMIT_HASH=$(git rev-parse --short HEAD)
DATAFLOW_URL=https://dataflow-fil.cfapps.io

# register the app
curl -X POST -d "uri=maven%3A%2F%2Fcom.github.EngineerBetter%3Acloud-task-batch%3A${COMMIT_HASH}" ${DATAFLOW_URL}/apps/task/app

# create a task definition
curl -X POST -d 'name=task&definition=app' ${DATAFLOW_URL}/tasks/definitions

# launch the task
curl -X POST -d 'name=task' ${DATAFLOW_URL}/tasks/executions