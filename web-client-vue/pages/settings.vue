<template>
  <v-layout>
    <v-flex text-xs-left>
      <blockquote class="blockquote">
        1. Insert code below in your page head section.
      </blockquote>
        <pre v-highlightjs>
            <code class="html">
            &lt;script type="javascript"&gt;
                (function (srcjs) {
                    window.__chat_project = window.__chat_project || {};
                    var doc = document.createElement('script');
                    doc.type = 'text/javascript';
                    doc.async = true;
                    doc.src = ('https:' == document.location.protocol ? 'https:' : 'http:') + srcjs;
                    var s = document.getElementsByTagName('script')[0];
                    s.parentNode.insertBefore(doc, s);
                })("//s3.eu-central-1.amazonaws.com/jhwh/chat_project.js");
            &lt;/script&gt;
            </code>
        </pre>
    </v-flex>
  </v-layout>
</template>






<style scoped>

.html {
  width: 100%;
}

.hljs {
  display: block;
  overflow-x: auto;
  padding: 0.5em;
  background: #F0F0F0;
}

/* Base color: saturation 0; */

.hljs,
.hljs-subst {
  color: #444;
}

.hljs-comment {
  color: #888888;
}

.hljs-keyword,
.hljs-selector-tag,
.hljs-meta-keyword,
.hljs-doctag,
.hljs-name {
  font-weight: bold;
}

.hljs-attribute {
  color: #0E9A00;
}    

.hljs-function {
  color: #99069A;
}

.hljs-builtin-name {
  color: #99069A;
}

/* User color: hue: 0 */

.hljs-type,
.hljs-string,
.hljs-number,
.hljs-selector-id,
.hljs-selector-class,
.hljs-quote,
.hljs-template-tag,
.hljs-deletion {
  color: #880000;
}

.hljs-title,
.hljs-section {
  color: #880000;
  font-weight: bold;
}

.hljs-regexp,
.hljs-symbol,
.hljs-variable,
.hljs-template-variable,
.hljs-link,
.hljs-selector-attr,
.hljs-selector-pseudo {
  color: #BC6060;
}


/* Language color: hue: 90; */

.hljs-literal {
  color: #78A960;
}

.hljs-built_in,
.hljs-bullet,
.hljs-code,
.hljs-addition {
  color: #0C9A9A;
}


/* Meta color: hue: 200 */

.hljs-meta {
  color: #1f7199;
}

.hljs-meta-string {
  color: #4d99bf;
}


/* Misc effects */

.hljs-emphasis {
  font-style: italic;
}

.hljs-strong {
  font-weight: bold;
  }
</style>
