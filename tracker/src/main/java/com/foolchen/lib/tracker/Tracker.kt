package com.foolchen.lib.tracker

import android.app.Application
import com.foolchen.lib.tracker.event.Event
import com.foolchen.lib.tracker.event.VIEW_SCREEN
import com.foolchen.lib.tracker.lifecycle.ActivityLifeCycle
import com.foolchen.lib.tracker.utils.log

/**
 *
 * @author chenchong
 * 2017/11/4
 * 上午11:17
 */
object Tracker {

  /** 上一个浏览页面的名称 */
  internal var refer: String = ""

  /**当前正在浏览的页面的名称*/
  internal var name: String = ""

  /**当前正在浏览的页面所依附的页面*/
  internal var parent: String = ""

  /**
   * 固定需要获取的属性，该属性在初始化时完成
   * 这些属性在所有的事件中都会存在
   */
  internal val regularProperties = HashMap<String, Any>()
  /**
   * 开发者在初始化时附加的属性
   * 这些属性在所有的事件中都会存在
   */
  internal val additionalProperties = HashMap<String, Any>()

  internal var distinctId = ""
  internal var userId: String? = null

  internal var isDebugEnable = false

  fun initialize(app: Application) {
    app.registerActivityLifecycleCallbacks(ActivityLifeCycle())
  }

  fun setDebugEnable(enable: Boolean) {
    isDebugEnable = enable
  }

  /**
   * 增加自定义属性
   */
  fun addProperty(key: String, value: Any?) {
    if (value != null) {
      additionalProperties.put(key, value)
    }
  }

  /**
   * 增加自定义属性
   */
  fun addProperties(properties: Map<String, Any?>?) {
    properties?.forEach({
      if (it.value != null) {
        additionalProperties.put(it.key, it.value!!)
      }
    })
  }

  /**
   * 用户登录
   * TODO
   */
  fun login(userId: String) {}

  /**
   * 用户登出
   * TODO
   */
  fun logout() {

  }

  internal fun trackScreen() {
    val event = Event(VIEW_SCREEN, name, refer, parent)
    log(event)

    // TODO: 2017/11/4 chenchong 用于暂存或者请求接口
  }

}