module.exports = function(context) {

   var path = context.requireCordovaModule('path');
   var ET = context.requireCordovaModule('elementtree');
   var ConfigFile = context.requireCordovaModule("cordova-common").ConfigFile;



      //
      // find the meta-data node in AndroidManifest.xml to copy the sender id into
      //
      var androidPrjDir = path.join(context.opts.projectRoot, 'platforms/android');
      var androidManifest = new ConfigFile(androidPrjDir, 'android', 'AndroidManifest.xml');

      var applicationNode = androidManifest.data.find('./application/activity[@android:name="MainActivity"]');
      var manifestGcmIdNode = applicationNode.find('meta-data[@android:name="cheshmakPush"]');

      if(!manifestGcmIdNode){
         manifestGcmIdNode = new ET.Element('meta-data', {'android:name': 'cheshmakPush'});
         applicationNode.append( manifestGcmIdNode );
      }

      manifestGcmIdNode.set('android:value', 'openActivityOnPush');
      androidManifest.save();





   return true;
}
