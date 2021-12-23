/*
 *
 * Copyright <2021> <Miguel Vicencio Gomez>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 *  of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 *   conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT
 * OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsm.mvicencio.news.services;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import cl.ucn.disc.dsm.mvicencio.databinding.ActivityMainBinding;
import cl.ucn.disc.dsm.mvicencio.news.NewsAdapter;

import cl.ucn.disc.dsm.mvicencio.news.model.NewsViewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainActivity extends AppCompatActivity {


  /**
   *  The Logger
   */
  private static final Logger log = LoggerFactory.getLogger(MainActivity.class);

  /**
   * The Binding
   */
  private ActivityMainBinding binding;

  /**
   * The NewsViewModel
   */
  private NewsViewModel newsViewModel;

  /**
   * The NewsAdapter
   */
  private NewsAdapter newsAdapter;

  /**
   * @param savedInstanceState the instance
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //Inflate the xml
    this.binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(this.binding.getRoot());

    // Set the toolbar
    this.setSupportActionBar(this.binding.toolbar);

    //Build the NewsViewModel
    this.newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

    //What to do with swipe?
    this.binding.amSrlRefresh.setOnRefreshListener(()->{
      log.debug("Refreshing the News...");
      this.newsViewModel.refresh();
    });

    //Instantiate the Adapter
    this.newsAdapter = new NewsAdapter();

    //Configure the RecycleView
    {
      // 1. Layout
      this.binding.amRvListNews.setLayoutManager(new LinearLayoutManager(this));
      // 2.The decoration
      this.binding.amRvListNews.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
      // 3. The Adapter
      this.binding.amRvListNews.setAdapter(this.newsAdapter);
    }

    //Observe the list of news
    this.newsViewModel.getNews().observe(this, news -> {

      log.debug("News: {}", news.size());

      //Notify the adapter with thew list of news
      this.newsAdapter.setNews(news);

      //Hide the rotating circle
      this.binding.amSrlRefresh.setRefreshing(false);

    } );

  }

  /**
   * On Start.
   */
  @Override
  protected void onStart() {
    super.onStart();
    log.debug("onStart ..");
  }


}
