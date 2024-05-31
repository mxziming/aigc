echo "Step1  提交到远程仓库"
git add .
git commit -m "update"
git push
echo "Step2  等待任意键退出"
read -p "按任意键退出"