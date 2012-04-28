/*
 * Copyright 2007 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
 /**
  * Tags for rendering links to feeds
  *
  * @author Marc Palmer (marc@anyware.co.uk)
  */
 class FeedsTagLib {
    
    static namespace = "feed"
    
    static FEED_TITLES = [
        rss:"RSS ",
        atom:"Atom "]
        
    static FEED_TYPES = [
        rss:'application/rss+xml',
        atom:'application/atom+xml'
    ]
    
    /**
     * Creates the rel=alternate meta tag for a feed type
     * Pass in controller, action, id etc as per normal createLink, as well as
     * kind="rss" or kind="atom" and version="x.y"
     */
    def meta = { attrs ->
        // @todo check kind + version supplied
        out << '<link rel="alternate" type="'
        def t = FEED_TYPES[attrs.kind] 
        if (!t) {
            throw new RuntimeException("Unsupported feed type: ${attrs.kind}")
        }
        out << t
        out << '" title="'
        out << FEED_TITLES[attrs.kind]
        out << attrs.version.encodeAsHTML()
        out << '" href="'
            out << g.createLink(attrs)
        out << '"/>'
    }
}