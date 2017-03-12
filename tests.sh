#!/usr/bin/env bash

#
# Helper function to test return values
# param 1 should be equal param2
#
# assert param1 param2 testname
#
function assert() {
    local value1="$1"
    local value2="$2"
    local testName="$3"

    if [ $# -ne 3 ];
    then
        echo Wrong amount of parameters
        echo call this function like 'assert value1 value2 testName'
        return 2
    fi

    if [ $value1 -ne $value2 ];
    then
        echo ERROR: $value1 does not equals $value2 in $testName
        return 1
    fi

    return 0
}

make target &>/dev/null

java -cp target/my-app-1.0-SNAPSHOT.jar homework.mstruebing.app.App &>/dev/null
assert "$?" 0 "call without any parameters should return 0"

java -cp target/my-app-1.0-SNAPSHOT.jar homework.mstruebing.app.App -h &>/dev/null
assert "$?" 0 "-h should return 0"

java -cp target/my-app-1.0-SNAPSHOT.jar homework.mstruebing.app.App --help &>/dev/null
assert "$?" 0 "--help should return 0"

java -cp target/my-app-1.0-SNAPSHOT.jar homework.mstruebing.app.App -r &>/dev/null
assert "$?" 2 "call with r option but no id should return 2"

java -cp target/my-app-1.0-SNAPSHOT.jar homework.mstruebing.app.App --remove &>/dev/null
assert "$?" 2 "call with remove option but no id should return 2"

java -cp target/my-app-1.0-SNAPSHOT.jar homework.mstruebing.app.App --remove a &>/dev/null
assert "$?" 2 "call with remove option but no valid id should return 2"

java -cp target/my-app-1.0-SNAPSHOT.jar homework.mstruebing.app.App --remove a1 &>/dev/null
assert "$?" 2 "call with remove option but no valid id should return 2"
