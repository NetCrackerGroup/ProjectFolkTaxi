#!/bin/bash
ssh $DEPLOY_USER@$DEPLOY_HOST ./upgradeScript
echo 'Hello'
echo 'World'