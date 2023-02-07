const baseConfig = require('@eclipse-scout/cli/scripts/webpack-defaults');

module.exports = (env, args) => {
  args.resDirArray = ['src/main/resources/WebContent', 'node_modules/@eclipse-scout/core/res'];
  const config = baseConfig(env, args);

  config.entry = {
    'scout': './src/main/js/scout.ts',
    'login': './src/main/js/login.ts',
    'logout': './src/main/js/logout.ts',
    'scout-theme': './src/main/js/scout-theme.less',
    'scout-theme-dark': './src/main/js/scout-theme-dark.less'
  };

  return config;
};
