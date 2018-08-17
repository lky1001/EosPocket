package app.eospocket.android.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TextUtils.class)
public class CustomPreferenceTest {

    @Mock
    Context context;

    @Mock
    SharedPreferences sharedPreferences;

    @Mock
    SharedPreferences.Editor editor;

    CustomPreference customPreference;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(context.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPreferences);

        mockStatic(TextUtils.class);

        when(TextUtils.equals(any(CharSequence.class), any(CharSequence.class))).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                CharSequence a = (CharSequence) invocation.getArguments()[0];
                CharSequence b = (CharSequence) invocation.getArguments()[1];
                if (a == b) return true;
                int length;
                if (a != null && b != null && (length = a.length()) == b.length()) {
                    if (a instanceof String && b instanceof String) {
                        return a.equals(b);
                    } else {
                        for (int i = 0; i < length; i++) {
                            if (a.charAt(i) != b.charAt(i)) return false;
                        }
                        return true;
                    }
                }
                return false;
            }
        });

        customPreference = new CustomPreference(context);
    }

    @Test
    public void testGetUsePinCode_success() {
        when(sharedPreferences.getString(any(), any())).thenReturn("");
        when(sharedPreferences.edit()).thenReturn(editor);

        customPreference.loadSettings();

        assertEquals(true, customPreference.getUsePinCode());
    }

    @Test
    public void returnFalseGetUsePinCode_success() {
        when(sharedPreferences.getString(any(), any())).thenReturn("{\"usePinCode\":false}");
        when(sharedPreferences.edit()).thenReturn(editor);

        customPreference.loadSettings();

        assertEquals(false, customPreference.getUsePinCode());
    }
}