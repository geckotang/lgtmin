log.info "Setting attributes"
String hash = params.hash
Image image = Image.findByHash(hash)
log.info "Image: ${image}"
if (image) {
  request.setAttribute 'image', image
  response.setHeader("Access-Control-Allow-Origin", "*");
  response.setHeader("Access-Control-Allow-Methods", "GET");
  response.setHeader("Access-Control-Allow-Credentials", "true");
  if (request.getHeader('Accept').contains('application/json')) {
    response.setHeader("Content-Type", "application/json");
    log.info "Forwarding to the template"
    forward '/WEB-INF/pages/show.json.gtpl'
  } else {
    response.setHeader("Content-Type", "text/html");
    forward '/WEB-INF/pages/show.html.gtpl'
  }
} else {
  redirect('/')
}