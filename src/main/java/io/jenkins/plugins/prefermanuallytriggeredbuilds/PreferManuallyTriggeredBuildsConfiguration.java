package io.jenkins.plugins.prefermanuallytriggeredbuilds;

import hudson.Extension;
import hudson.util.FormValidation;
import jenkins.model.GlobalConfiguration;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.QueryParameter;

/**
 * The Jenkins global configuration of the Prefer Manually Triggered Builds plugin.
 */
@Extension
public class PreferManuallyTriggeredBuildsConfiguration extends GlobalConfiguration {

    /** @return the singleton instance */
    public static PreferManuallyTriggeredBuildsConfiguration get() {
        return GlobalConfiguration.all().get(PreferManuallyTriggeredBuildsConfiguration.class);
    }

    private Boolean enabled;

    public PreferManuallyTriggeredBuildsConfiguration() {
        // When Jenkins is restarted, load any saved configuration from disk.
        load();
    }

    /** @return the currently configured label, if any */
    public boolean isEnabled() {
        return enabled.booleanValue();
    }

    /**
     * Together with {@link #isEnabled}, binds to entry in {@code config.jelly}.
     * @param enabled the new value of this field
     */
    @DataBoundSetter
    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
        save();
    }

    public FormValidation doCheckEnabled(@QueryParameter Boolean value) {
        if (value.booleanValue()) {
            return FormValidation.ok("Manually triggered builds will be prefered in build queue.");
        } else {
            return FormValidation.ok();
        }
    }

}
