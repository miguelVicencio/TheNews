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

package cl.ucn.disc.dsm.mvicencio.news;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import cl.ucn.disc.dsm.mvicencio.R;
import cl.ucn.disc.dsm.mvicencio.databinding.RowNewsBinding;
import cl.ucn.disc.dsm.mvicencio.news.model.News;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.ocpsoft.prettytime.PrettyTime;
import org.threeten.bp.DateTimeUtils;

/**
 * The Adapter of News
 *
 * @author Miguel Vicencio Gomez
 */
public final class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    /**
     * The PrettyTime
     */
    private static final PrettyTime PRETTY_TIME = new PrettyTime();

    /**
     * DataSource
     */
    private List<News> theNews = new ArrayList<>();

    /**
     * The Costructor
     */
    public NewsAdapter() {
        //Nothing here
    }

    /**
     * Update the datasource
     *
     * @param news to use as data source
     */
    public void setNews(final List<News> news){
        this.theNews = news;

        //Notify  the RecyclerView
        this.notifyDataSetChanged();
    }

    /**
     * Called when the RecyclerView need a fresh row of NewsViewHolder.
     *
     * @param parent The viewGroup where the new will be added
     * @param viewType the view of this type
     * @return the NewsViewHolder ready for action
     */
    @Override
    public NewsViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        //Call the constructor inflating the layout
        return new NewsViewHolder(
                RowNewsBinding.inflate(
                        LayoutInflater.from(parent.getContext())
                )
        );
    }

    /**
     * Called by the RecyclerView when need to display some data at specific position
     *
     * @param holder to use to set the GUI data
     * @param position of the dataset to show
     */
    @Override
    public void onBindViewHolder(final NewsViewHolder holder, int position) {
        //Bind the ViewHolder + News at certain position.
        holder.bind(this.theNews.get(position));

    }

    /**
     *
     * @return the size of the dataset
     */
    @Override
    public int getItemCount() {
        return this.theNews.size();
    }

    /**
     * The viewsHolder implementation of News
     */
    public static class NewsViewHolder extends RecyclerView.ViewHolder{

        /**
         * The GUI of News.
         */
        private final RowNewsBinding rowNewsBinding;

        /**
         *
         * @param rowNewsBinding the layout to use
         */
        public NewsViewHolder(final RowNewsBinding rowNewsBinding) {
            super(rowNewsBinding.getRoot());
            this.rowNewsBinding = rowNewsBinding;
        }

        /**
         *
         * @param news to use.
         */
        public void bind(final News news){

            //BInd the title
            this.rowNewsBinding.rnTvTitle.setText(news.getTitle());

            //Bind the author
            this.rowNewsBinding.rnTvAuthor.setText(news.getAuthor());

            //Bind the source
            this.rowNewsBinding.rnTvSource.setText(news.getSource());

            //Bind the description
            this.rowNewsBinding.rnTvDescription.setText(news.getDescription());

            //zonedDateTime to Date
            final Date theDate = DateTimeUtils.toDate(news.getPublishedAt().toInstant());
            //Bind the Date
            this.rowNewsBinding.rnTvPublishedAt.setText(PRETTY_TIME.format(theDate));

            //Bind the image
            if (news.getUrlImage() != null){
                final Uri uri = Uri.parse(news.getUrlImage());
                this.rowNewsBinding.rnSdvImage.setImageURI(uri);
            } else{
                //No image, use the default
                this.rowNewsBinding.rnSdvImage.setImageResource(R.drawable.ic_launcher_foreground);
            }

        }
    }
}
