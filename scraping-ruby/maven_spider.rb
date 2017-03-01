# Read README.md first

require 'rubygems'
require 'nokogiri'
require 'open-uri'

root_url = "http://central.maven.org/maven2/"
$JAR_EXTENSIONS = ['jar', 'sha1', 'md5']
$TERMINAL_EXTENSIONS = ['pom', 'xml'] + $JAR_EXTENSIONS
$NON_PROCEEDABLE_DIRS = ['Parent Directory', '..']

def get_current_url(path)
	return (path.end_with? '/') ? path : path + '/'
end

def get_canonical_name(this_node, child)
	return this_node[:canonical_name] + ' >> ' + child
end

def get_page_content_as_nokogiri_obj(url)
	page = ""
	open(url) { |io| page = io.read }
	pageobj = Nokogiri::HTML(page)

	return pageobj
end

def is_valid_node(url)
	url.end_with?('/') || is_valid_jarshaxml1(url)
end	

def need_not_proceed(href, text) 
	return href.to_s.empty? || text.to_s.empty? || $NON_PROCEEDABLE_DIRS.include?(text)
end


def terminal_link(link)
	$TERMINAL_EXTENSIONS.any? { |extn| link.end_with?(extn) }
end

def is_jar_link(link)
	$JAR_EXTENSIONS.any? { |extn| link.end_with?(extn) }
end

def append(url1, url2)
	url1 = (url1 + "/") unless url1.end_with?("/")
	url2 = (url1 + "/") unless url2.end_with?("/")
	url1 + url2
end

def is_relative_link(href)
	href.end_with? ('/')
end

root_node = {
	:name => "root",
	:direct_link => root_url,
	:canonical_name => "root",
	:parent_url => "",
	:processed => false,
	:is_a_dependency => false
}

$urls = {}
$urls[root_url] = root_node

def scrape(url)
	# Forced line to cut very deep recursion so that we can test POC
	return if ($urls.size > 10000)

	node = $urls[url]

	# If page already scraped, abort scraping that page and return
	if node[:processed]
		puts "Scraping aborted for #{url}; REASON: Already scraped"
	end	
	
	# If one of the terminal links like XML, JAR, SHA1, mark the node processed and return; No scraping required
	if terminal_link(url)
		node[:processed] = true
		node[:is_a_dependency] = true if is_jar_link(url)
		puts "Scraping aborted for #{url}; REASON: Terminal Link encountered."
		return
	end	

	node[:processed] = true

	# Attempt to fetch a page; If 404, abort scraping that page and return
	pageobj = nil
	begin
		pageobj = get_page_content_as_nokogiri_obj(url)
	rescue => ex
		puts "Scraping aborted for #{url}; REASON: Exception #{ex} while opening."
		return
	end	

	# Extract all anchor tags. If no anchor tags, abort scraping and return.
	atags = pageobj.css('a')
	if atags.to_a.empty?
		puts "Scraping aborted for #{url}; REASON: No anchor tags found"
		return 
	end

	# Process each anchor tag in the page
	atags.each do |atag|
		href = atag['href']
		# puts "\nhref: #{href}"
		text = atag.text.to_s.empty? ? "" : atag.text.chomp('/')

		# Do not go deep if the text on link is 'Parent Directory' or if that is an empty link
		if (need_not_proceed(href, text))
			# puts "Chasing: NO"
			next
		end

		# Get detailed info about each anchor tag
		child_node = {}
		child_node[:name] = text
		direct_link_to_this_node = is_relative_link(href) ? append(node[:direct_link], href) : href
		child_node[:direct_link] = direct_link_to_this_node
		child_node[:canonical_name] = get_canonical_name(node, child_node[:name])
		child_node[:parent_url] = url
		child_node[:processed] = false

		# if the urls map already contains this node's direct link, skip initiating a call to that URL.
		if !$urls.has_key?(direct_link_to_this_node)
			$urls[direct_link_to_this_node] = child_node	
			# puts "\nChasing #{direct_link_to_this_node}"
			scrape(direct_link_to_this_node)
		end
	end
end

scrape(root_url)
puts "\n\nList of all libraries found: \n"
$urls.each do |direct_url, node|
	puts "#{node[:canonical_name]}" if node[:is_a_dependency]
end

