# widget-chat-project

> Nuxt.js project

## Build Setup

``` bash
# install dependencies
$ npm install # Or yarn install

# serve with hot reload at localhost:3000
$ npm run dev


Example JavaScript code:
```html
<script type="text/javascript">
	(function (srcjs) {
		window.__chat_project = window.__chat_project || {};
		var doc = document.createElement('script');
		doc.type = 'text/javascript';
		doc.async = true;
		doc.src = ('https:' == document.location.protocol ? 'https:' : 'http:') + srcjs;
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(doc, s);
	})("//s3.eu-central-1.amazonaws.com/jhwh/chat_project.js");
</script>

```
After run dev server
Check the [DEMO](http://chat-project.s3-website-eu-west-1.amazonaws.com).



For detailed explanation on how things work, checkout the [Nuxt.js docs](https://github.com/nuxt/nuxt.js).
