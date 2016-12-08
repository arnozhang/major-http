/**
 * Android Jungle-Easy-Http framework project.
 *
 * Copyright 2016 Arno Zhang <zyfgood12@163.com>
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

package com.jungle.easyhttp.request.queue;

import com.android.volley.toolbox.HurlStack;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpsStack extends HurlStack {

    private HostnameVerifier mHostnameVerifier;


    public HttpsStack() {
        this(null);
    }

    public HttpsStack(UrlRewriter urlRewriter) {
        this(urlRewriter, null);
    }

    public HttpsStack(UrlRewriter urlRewriter, SSLSocketFactory sslSocketFactory) {
        super(urlRewriter, sslSocketFactory);
    }

    public HttpsStack(UrlRewriter urlRewriter, SSLSocketFactory sslSocketFactory, HostnameVerifier verifier) {
        super(urlRewriter, sslSocketFactory);
        mHostnameVerifier = verifier;
    }

    @Override
    protected HttpURLConnection createConnection(URL url) throws IOException {
        HttpURLConnection connection = super.createConnection(url);
        if ("https".equals(url.getProtocol()) && mHostnameVerifier != null) {
            ((HttpsURLConnection) connection).setHostnameVerifier(mHostnameVerifier);
        }

        return connection;
    }
}
