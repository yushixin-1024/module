#! /bin/bash
# SpringBoot jar包路径
APP_NAME=/module/tools-0.0.1.jar

# 使用说明，用来提示输入参数
usage() {
	echo "Usage:  sh 执行脚本.sh [ start | stop | restart | status ]"
	exit 1
}

# 检查程序是否正在运行
is_running() {
	pid=`ps -ef|grep $APP_NAME|grep -v grep|awk '{print $2}' `
	# 如果未运行返回1，正在运行返回1
	if [ -z "${pid}" ]; then
		return 1
	else
		return 0
	fi
}

# 启动方法
start() {
	is_running
	if [ $? -eq "0" ]; then
		echo "${APP_NAME} is running. pid=${pid}."
	else
		# 启动应用，不输出日志
		#nohup java -jar ${APP_NAME} > dev/null  2>&1 &
		# 启动应用，输出日志
		nohup java -jar ${APP_NAME} > tools.out  2>&1 &
	fi
}

# 停止方法
stop() {
	is_running
	if [ $? -eq "0" ]; then
		kill -9 ${pid}
	else
		echo "${APP_NAME} is stoped."
	fi
}

# 重启方法
restart() {
	stop
	start
}

# 运行状态
status() {
	is_running
	if [ $? -eq "0" ]; then
		echo "${APP_NAME} is running. pid=${pid}."
	else
		echo "${APP_NAME} is stoped."
	fi
}

# 根据输入参数，执行对应方法，不输入则执行使用说明
case "$1" in
	"start")
		start
		;;
	"stop")
		stop
		;;
	"restart")
		restart
		;;
	"status")
		status
		;;
	*)
		usage
		;;
esac