#!/usr/bin/env bash
echo "Starting PPaaS Thrift Event Publisher..."

if [ -z "$JAVA_HOME" ]; then
  echo "You must set the JAVA_HOME variable before running PPaaS Thrift Event Publisher."
  exit 1
fi

script_path="$( cd -P "$( dirname "$SOURCE" )" && pwd )/`dirname $0`"
lib_path=${script_path}/../lib/
class_path=`echo ${lib_path}/*.jar | tr ' ' ':'`
properties="-Dthrift.event.publisher.properties=${script_path}/../conf/PPaaSThriftEventPublisher.properties
            -Devent.user.data.path=${script_path}/../data/EventsData.tsv
            -Dlog4j.configuration=file://${script_path}/../conf/log4j.properties"

# Uncomment below line to enable remote debugging
#debug="-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005"
$JAVA_HOME/bin/java -cp "${class_path}" ${properties} ${debug} org.wso2.ppaas.thrift.publisher.Main $*