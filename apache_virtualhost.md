# 아파치에서 버츄얼호스트에 대해 알아보자!

1. 경로
```
etc => httpd => conf => httpd.conf
```

2. ex) 도메인 셋팅
```
# test.co.kr #
<VirtualHost *:80>
AddDefaultCharset	UTF-8
ServerAdmin			codej625@dev.co.kr
DocumentRoot		/home/vhosts/codej625.kr/www // ex) /home/vhosts/testpage 
ServerName			www.codej625dev.co.kr
ServerAlias			codej625dev.co.kr
ErrorLog			"|/usr/sbin/rotatelogs -l /home/vhosts/codej625.kr/logs/www/error-%Y-%m-%d.log 86400"
CustomLog			"|/usr/sbin/rotatelogs -l /home/vhosts/codej625.kr/logs/www/access-%Y-%m-%d.log 86400" combined
DirectoryIndex		index.html index.php // <= default page

<Directory /home/vhosts/codej625.kr/www>
	AddType application/x-httpd-php .php
	AllowOverride FileInfo
	Order allow,deny
	Allow from all       

	ErrorDocument 401 "/error_404.html"
	ErrorDocument 403 "/error_404.html"
	ErrorDocument 404 "/error_404.html"
	ErrorDocument 405 "/error_404.html"
	ErrorDocument 500 "/error_500.html"
	ErrorDocument 501 "/error_500.html"
	ErrorDocument 502 "/error_500.html"
	ErrorDocument 504 "/error_500.html"

	<LimitExcept GET HEAD>
		Order allow,deny
		Deny from all
	</LimitExcept>	
</Directory>

# <IfModule mod_rewrite.c>
# 	RewriteRule ^ https://www.%{HTTP_HOST}%{REQUEST_URI} [L,R=301]
# </IfModule>
	
</VirtualHost>
```
