package archmages.github.dyweather.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:lmyu@wisorg.com">David.Yu</a>
 * @version V1.0, 12/12/15
 */
public class Tmp {

    @SerializedName("max")
    @Expose
    private String max;
    @SerializedName("min")
    @Expose
    private String min;

    /**
     *
     * @return
     * The max
     */
    public String getMax() {
        return max;
    }

    /**
     *
     * @param max
     * The max
     */
    public void setMax(String max) {
        this.max = max;
    }

    /**
     *
     * @return
     * The min
     */
    public String getMin() {
        return min;
    }

    /**
     *
     * @param min
     * The min
     */
    public void setMin(String min) {
        this.min = min;
    }

}
