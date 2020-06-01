#!/usr/bin/env bash
#----------------------------------------------------------
# Set up environment variable OOZIE_URL
#----------------------------------------------------------

OOZIE_URL="http://tmdb_movies01.it-soft.ru:10000/oozie"
export OOZIE_URL="${OOZIE_URL}"

#----------------------------------------------------------
# Set up Unix FS and HDFS path to config files
#----------------------------------------------------------

PROJECT_NAME=${PROJECT_NAME}
LINUX_PROJECT_PATH=${PROJECT_DIR}/oozie/${PROJECT_NAME}
PROJECT_PATH=/user/${USER}/Tasks/${PROJECT_NAME}
WORKING_DIR=${PROJECT_PATH}/work
TARGET_JAR=${TARGET_JAR}

#----------------------------------------------------------
# Clear current workflow directory from HDFS
# and upload updated workflow config files
#----------------------------------------------------------

echo 'Copying Oozie configs to HDFS...'

hadoop fs -rm -R ${WORKING_DIR}
hadoop fs -mkdir -p ${WORKING_DIR}
hadoop fs -put ${LINUX_PROJECT_PATH}/* ${WORKING_DIR}

echo 'Done.'

#----------------------------------------------------------
# Create tables
#----------------------------------------------------------

echo 'Start insert into new table...'

echo 'Done'

#----------------------------------------------------------
# Start Oozie job
#----------------------------------------------------------

echo 'Starting coordinator'
oozie job -run \
-auth KERBEROS \
-config job.properties  \
-D USER=${USER} \
-D PROJECT_NAME=${PROJECT_NAME} \
-D WORKING_DIR=${WORKING_DIR} \
-D TARGET_JAR=${TARGET_JAR}

