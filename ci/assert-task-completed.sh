#!/bin/bash

set -eux

EXPECTED_MESSAGE='and the following status: \[COMPLETED\]'

CF_API=https://api.run.pivotal.io

cf api ${CF_API}
cf auth ${CF_USERNAME} ${CF_PASSWORD}
cf target -o ${CF_ORG} -s ${CF_SPACE}

cf logs task --recent | grep -qE "${EXPECTED_MESSAGE}"

echo Task completed succesfully