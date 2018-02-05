package com.serionz.newsfeed.main.global_news

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsClient
import android.support.customtabs.CustomTabsIntent
import android.support.customtabs.CustomTabsServiceConnection
import android.support.customtabs.CustomTabsSession
import android.support.design.widget.BottomSheetDialog
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.serionz.newsfeed.R
import com.serionz.newsfeed.main.ArticleMenu
import com.serionz.newsfeed.main.ArticleMenuAdapter
import java.util.ArrayList
import java.util.Collections
import java.util.HashMap

class GlobalNewsFragment : Fragment(), SendNews, GlobalNewsViewAdapter.SelectedArticle, ArticleMenuAdapter.ArticleMenuInterface {
    private val CUSTOM_TAB_PACKAGE_NAME = "com.android.chrome"
    private var mListener: OnFragmentInteractionListener? = null
    private var recyclerView: RecyclerView? = null
    private var mController: Controller? = null
    private var mGlobalNewsViewAdapter: GlobalNewsViewAdapter? = null
    private var mArticleMenuAdapter: ArticleMenuAdapter? = null
    private val mArticleList = ArrayList<Article>()
    private var newsSources: HashMap<String, Int>? = null

    private var mCustomTabsClient: CustomTabsClient? = null
    private var mCustomTabsSession: CustomTabsSession? = null
    private var mCustomTabsServiceConnection: CustomTabsServiceConnection? = null
    private var mCustomTabsIntent: CustomTabsIntent? = null

    private var bottomSheetDialog: BottomSheetDialog? = null
    private var articleMenuView: View? = null
    private var selectedArticle: Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_global_news, container, false)

        bottomSheetDialog = BottomSheetDialog(context)
        articleMenuView = layoutInflater.inflate(R.layout.fragment_article_menu, null)
        bottomSheetDialog!!.setContentView(articleMenuView)
        newsSources = object : HashMap<String, Int>() {
            init {
                put("bbc-news", R.drawable.bbc_logo)
                put("cnn", R.drawable.cnn_logo)
                put("al-jazeera-english", R.drawable.aljazeera_logo)
                put("bloomberg", R.drawable.bloomberg_logo)
                put("business-insider", R.drawable.business_logo)
                put("buzzfeed", R.drawable.buzzfeed_logo)
            }
        }

        val articleMenus = object : ArrayList<ArticleMenu>() {
            init {
                add(ArticleMenu("Share", R.drawable.ic_share_black_24dp))
                add(ArticleMenu("Hide", R.drawable.ic_visibility_off_black_24dp))
                add(ArticleMenu("Not interested in", R.drawable.ic_close_black_24dp))
                add(ArticleMenu("Report Story", R.drawable.ic_report_black_24dp))
                add(ArticleMenu("Customize Stories", R.drawable.ic_settings_black_24dp))
            }
        }

        val articleMenuRecyclerView = articleMenuView!!.findViewById<View>(R.id.article_menu_list) as RecyclerView
        mArticleMenuAdapter = ArticleMenuAdapter(this, articleMenus)
        articleMenuRecyclerView.layoutManager = LinearLayoutManager(articleMenuView!!.context)
        articleMenuRecyclerView.itemAnimator = DefaultItemAnimator()
        articleMenuRecyclerView.adapter = mArticleMenuAdapter

        recyclerView = view.findViewById<View>(R.id.news_list) as RecyclerView

        mGlobalNewsViewAdapter = GlobalNewsViewAdapter(this, mArticleList, newsSources)
        recyclerView!!.layoutManager = LinearLayoutManager(view.context)
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = mGlobalNewsViewAdapter

        mController = Controller(context)
        mController!!.fetchGlobalNews(this, newsSources)
        mGlobalNewsViewAdapter!!.notifyDataSetChanged()

        mCustomTabsServiceConnection = object : CustomTabsServiceConnection() {
            override fun onServiceDisconnected(componentName: ComponentName) {
                mCustomTabsClient = null
            }

            override fun onCustomTabsServiceConnected(name: ComponentName, client: CustomTabsClient) {
                mCustomTabsClient = client
                mCustomTabsClient!!.warmup(0L)
                mCustomTabsSession = mCustomTabsClient!!.newSession(null)
            }
        }

        CustomTabsClient.bindCustomTabsService(context, CUSTOM_TAB_PACKAGE_NAME, mCustomTabsServiceConnection)
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun receivedNews(newsList: NewsList) {
        val oldArticles = ArrayList<Article>()
        oldArticles.addAll(mArticleList)
        oldArticles.addAll(newsList.articles)

        Collections.sort(oldArticles, Article.ArticleComparator)
        mArticleList.clear()
        mArticleList.addAll(oldArticles)

        this.mGlobalNewsViewAdapter!!.notifyDataSetChanged()
    }

    override fun OnArticleClick(url: Uri) {
        mCustomTabsIntent = CustomTabsIntent.Builder(mCustomTabsSession)
                .setShowTitle(true)
                .setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSecondaryToolbarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
                .build()
        mCustomTabsIntent!!.launchUrl(context, url)
    }

    override fun OnArticleMenuClick(article: Article) {
        selectedArticle = article
        bottomSheetDialog!!.show()
    }

    override fun OnMenuItemClick(articleMenu: ArticleMenu) {
        bottomSheetDialog!!.dismiss()
        when (articleMenu.menuIcon) {
            R.drawable.ic_share_black_24dp -> createShareIntent()
        }
    }

    override fun OnShareArticleClick(article: Article) {
        this.selectedArticle = article
        createShareIntent()
    }

    private fun createShareIntent() {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, this.selectedArticle!!.title)
        shareIntent.putExtra(Intent.EXTRA_TEXT, this.selectedArticle!!.url)
        startActivity(shareIntent)
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        private val TAG = "Global News fragment"
    }
}// Required empty public constructor
