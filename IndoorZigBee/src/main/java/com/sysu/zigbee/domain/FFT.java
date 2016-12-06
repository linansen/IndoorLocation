package com.sysu.zigbee.domain;

public class FFT {
	/**
     * FFT
     * @param Re float[] 原始数据 返回时保存实部
     * @param Im float[]  返回时保存虚部
     * @param Flag int   1正变换  -1反变换
     */

    public void fft(double Re[], double Im[],int Flag) {
        if (Re == null || Im == null) {
            return;
        }
        int N = Re.length;
        if (N > Im.length) {
            N = Im.length;
        }
        int i, j, k, l, m, n1, n2;
        m = 0;
        double c, c1, e, s, s1, t, tr, ti;
        for (j = 1, i = 1; i < 16; i++) {
            m = i;
            j = 2 * j;
            if (j == N) {
                break;
            }
        }
        n1 = N - 1;
        for (j = 0, i = 0; i < n1; i++) {
            if (i < j) {
                tr = Re[j];
                ti = Im[j];
                Re[j] = Re[i];
                Im[j] = Im[i];
                Re[i] = tr;
                Im[i] = ti;
            }
            k = N / 2;
            while (k < (j + 1)) {
                j = j - k;
                k = k / 2;
            }
            j = j + k;
        }
        n1 = 1;
        for (l = 1; l <= m; l++) {
            n1 = 2 * n1;
            n2 = n1 / 2;
            e = java.lang.Math.PI / n2;
            c = 1;
            s = 0;
            c1 = java.lang.Math.cos(e);
            s1 = -Flag * java.lang.Math.sin(e);
            for (j = 0; j < n2; j++) {
                for (i = j; i < N; i += n1) {
                    k = i + n2;
                    if (k > N - 1) {
                        k = N - 1;
                    }
                    tr = c * Re[k] - s * Im[k];
                    ti = c * Im[k] + s * Re[k];
                    Re[k] = Re[i] - tr;
                    Im[k] = Im[i] - ti;
                    Re[i] = Re[i] + tr;
                    Im[i] = Im[i] + ti;
                }
                t = c;
                c = c * c1 - s * s1;
                s = t * s1 + s * c1;
            }
        }
        if (Flag == -1)
        {
            for (i = 0; i < N; i++) {
                Re[i] /= (double) N;
                Im[i] /= (double) N;
            }
        }
        if (Flag == 1) { //
            for (i = 0; i < N; i++) {
                if (i > Re.length - 1) {
                    continue;
                }
                if (i > Im.length - 1) {
                    continue;
                }
                Re[i] = (Re[i]*Re.length)/(float) N;
                Im[i] = (Im[i]*Im.length)/(float) N;
            }
        }
    }

}
