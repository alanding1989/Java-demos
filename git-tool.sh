#! /usr/bin/env bash


# File Name    : 0_Dev/Projects/gitcode.sh
# Author       : AlanDing
# Created Time : Mon 21 Oct 2019 04:28:07 PM CST
# Description  : 



# git remote add upstream git@github.com:lihengming/spring-boot-api-project-seed.git
# git remote add upstream git@github.com:zhengqingya/code-generator.git
#
# git remote add upstream git@github.com:cicadasmile/linux-system-base.git
#
# git remote add upstream git@github.com:cicadasmile/spring-mvc-parent.git
# git remote add upstream git@github.com:cicadasmile/spring-boot-base.git
# git remote add upstream git@github.com:cicadasmile/spring-cloud-base.git
# git remote add upstream git@github.com:cicadasmile/middle-ware-parent.git
# git remote add upstream git@github.com:xkcoding/spring-boot-demo.git


# [submodule "1.Java基础-demo/algorithm"]
  # path = 1.Java基础-demo/algorithm
  # url = git@github.com:wangzheng0822/algo.git


if [ $1 == 'gl' ]; then
  git submodule foreach 'git pull upstream master || git pull upstream sbt ||:'
elif [ $1 == 'gp' ]; then
  git submodule foreach 'git push upstream master || git push upstream sbt ||:'
elif [ $1 == 'copy' ]; then
  cp -rf "$(pwd)/modconfig-formemo/modules" "$(pwd)/.git/"
fi

