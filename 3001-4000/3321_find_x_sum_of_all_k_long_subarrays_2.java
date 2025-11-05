/*
// If two objects are equal according to equals(), // then they must return the same hashCode(). // 2. Integer, String, Double, etc. don’t use memory address // They override hashCode() to reflect the logical value, not the object’s identity.
*/
public class Solution {
    class Element {
        int value;
        int freq;
        Element(int value, int freq) { this.value = value; this.freq = freq; }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            Element other = (Element) obj;
            return this.value == other.value && this.freq == other.freq;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, freq);
        }
    }

    // comparator: higher freq first, tie -> higher value first
    private final Comparator<Element> comp = (a, b) -> {
        if (a.freq != b.freq) return Integer.compare(b.freq, a.freq);
        if (a.value != b.value) return Integer.compare(b.value, a.value);
        return 0;
    };

    public long[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        if (k > n) return new long[0];

        Map<Integer, Integer> hmap = new HashMap<>();
        TreeSet<Element> xset = new TreeSet<>(comp);   // top-x (by freq desc, value desc)
        TreeSet<Element> remset = new TreeSet<>(comp); // the rest
        long[] ans = new long[n - k + 1];
        long xsum = 0;
        int idx = 0;

        for (int i = 0; i < n; ++i) {
            int num = nums[i];
            int prevFreq = hmap.getOrDefault(num, 0);

            // remove old record (if any) and subtract from xsum if it was in xset
            if (prevFreq > 0) {
                Element old = new Element(num, prevFreq);
                if (xset.contains(old)) {
                    xsum -= (long) old.value * old.freq;
                    xset.remove(old);
                } else {
                    remset.remove(old);
                }
            }

            // add incremented frequency
            int newFreq = prevFreq + 1;
            hmap.put(num, newFreq);
            Element added = new Element(num, newFreq);
            xset.add(added);
            xsum += (long) added.value * added.freq;

            // ensure only top-x are in xset
            if (xset.size() > x) {
                Element last = xset.last(); // lowest among top-x
                xsum -= (long) last.value * last.freq;
                xset.remove(last);
                remset.add(last);
            }

            // when window exceeds size k, remove outgoing element nums[i-k]
            if (i >= k) {
                int out = nums[i - k];
                int freqOut = hmap.get(out);
                Element outElem = new Element(out, freqOut);

                if (xset.contains(outElem)) {
                    xsum -= (long) outElem.value * outElem.freq;
                    xset.remove(outElem);
                } else {
                    remset.remove(outElem);
                }

                if (freqOut == 1) {
                    hmap.remove(out);
                } else {
                    int reduced = freqOut - 1;
                    hmap.put(out, reduced);
                    // add reduced version to remset (will be promoted if needed)
                    remset.add(new Element(out, reduced));
                }

                // rebalance: promote from remset (highest freq there) while xset has space
                while (xset.size() < x && !remset.isEmpty()) {
                    Element promote = remset.first();
                    remset.remove(promote);
                    xset.add(promote);
                    xsum += (long) promote.value * promote.freq;
                }
            }

            // once first full window is formed, record answer
            if (i >= k - 1) {
                ans[idx++] = xsum;
            }
        }

        return ans;
    }
}
