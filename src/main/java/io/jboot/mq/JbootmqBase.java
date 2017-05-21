/**
 * Copyright (c) 2015-2017, Michael Yang 杨福海 (fuhai999@gmail.com).
 * <p>
 * Licensed under the GNU Lesser General Public License (LGPL) ,Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jboot.mq;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public abstract class JbootmqBase<M> implements Jbootmq<M> {

    private List<JbootmqMessageListener<M>> listeners = new CopyOnWriteArrayList<>();


    @Override
    public void addMessageListener(JbootmqMessageListener<M> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(JbootmqMessageListener<M> listener) {
        listeners.remove(listener);
    }

    @Override
    public void removeAllListeners() {
        listeners.clear();
    }

    @Override
    public List<JbootmqMessageListener<M>> getListeners() {
        return listeners;
    }

    public void notifyListeners(String channel, M message) {
        if (listeners == null || listeners.size() == 0) {
            return;
        }

        for (JbootmqMessageListener listener : listeners) {
            listener.onMessage(channel, message);
        }
    }
}
