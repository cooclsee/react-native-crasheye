
Pod::Spec.new do |s|
  s.name         = "RNCrasheye"
  s.version      = "1.0.0"
  s.summary      = "RNCrasheye"
  s.description  = <<-DESC
                  RNCrasheye
                   DESC
  s.homepage     = ""
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "cooclsee" => "cooclsee@gmail.com" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/cooclsee/react-native-crasheye.git", :tag => "master" }
  s.source_files  = "RNCrasheye/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  #s.dependency "others"

end

  