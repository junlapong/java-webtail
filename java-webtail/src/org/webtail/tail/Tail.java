package org.webtail.tail;


public class Tail {

	private LogFile file;
	
	public Tail(LogFile file) {
		this.file = file;
		
	}
	
	 public String tailLog(int lines) {
		    try {
		        java.io.RandomAccessFile fileHandler = new java.io.RandomAccessFile( file.getFile(), "r" );
		        long fileLength = file.getFileSize() - 1;
		        StringBuilder sb = new StringBuilder();
		        int line = 0;

		        for( long filePointer = fileLength; filePointer != -1; filePointer-- ) {
		            fileHandler.seek( filePointer );
		            int readByte = fileHandler.readByte();
	            	// 0xA == newline
		            if( readByte == 0xA ) {
		            	if(sb.length() != 0) {
		            		line += 1;
		            	}
		                if (line == lines) {
		                    if (filePointer == fileLength) {
		                        continue;
		                    } else {
		                        break;
		                    }
		                }
		            // 0xD carriage return
		            } else if( readByte == 0xD ) {
		            	line += 1;
		                if (line == lines) {
		                    if (filePointer == fileLength - 1) {
		                        continue;
		                    } else {
		                        break;
		                    }
		                }
		            }
		           sb.append( ( char ) readByte );
		        }

		        sb.deleteCharAt(sb.length()-1);
		        String lastLine = sb.reverse().toString();
		        lastLine = "<span>" + lastLine + "</span>";
		        return lastLine;
		    } catch( java.io.FileNotFoundException e ) {
		        e.printStackTrace();
		        return null;
		    } catch( java.io.IOException e ) {
		        e.printStackTrace();
		        return null;
		    }
		}


}